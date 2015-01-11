(ns clj-mini-apps.tic-tac-toe
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]

            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "ttt init"))))

(defn new-board []
  [[nil nil nil]
   [nil nil nil]
   [nil nil nil]])

(def regions
  (for [x [79 229 379]
        y [79 229 379]
        :let [[cx cy] [(+ x 70)
                       (+ y 70)]
              [row col] [(dec (quot cx 141))
                         (dec (quot cy 141))]]]
    {:x x :y y
     :w 141 :h 141
     :center [cx cy]
     :tile [row col]}))

(defn init-board []
  ;Create background and outline
  (q/smooth)
  (q/background 209 209 210)
  (q/fill 255)
  (q/no-stroke)
  (q/rect 9 9 582 582)
  ;Create tic tac toe grid
  (q/fill 0)
  (q/rect 220 79 9 441)
  (q/rect 370 79 9 441)
  (q/rect 79 220 441 9)
  (q/rect 79 370 441 9)
  (q/rect-mode :center)
  {:board (new-board)
   :regions regions})

(defn tile->region
  [regions [mx my]]
  (letfn [(is-in-region? [{:keys [x y w h]} [mx my]]
            (and (> mx x)
                 (< mx (+ x w))
                 (> my y)
                 (< my (+ y h))))]
    (->> regions
      (filter #(is-in-region? % [mx my]))
      (first))))

(defn tile->center
  [regions [tx ty]]
  (->> regions
       (map (fn [{:keys [center tile]}]
              (when (= [tx ty] tile)
                center)))
       (filter identity)
       (first)))

(defn add-circle
  [state [tx ty]]
  (assoc-in state [:board tx ty] :o))

(defn cpu-move [state]
  (let [get-rand-tile (fn [] [(rand-int 3) (rand-int 3)])]
    (loop [[tx ty] (get-rand-tile)]
      (if (not (get-in state [:board tx ty]))
        (assoc-in state [:board tx ty] :x)
        (recur (get-rand-tile))))))

(defn reset-game! [state]
  (do (q/rect-mode :corner)
      (q/background 209 209 210)
      (q/fill 255)
      (q/rect 9 9 582 582)
      (q/fill 0)
      (q/rect 220 79 9 441)
      (q/rect 370 79 9 441)
      (q/rect 79 220 441 9)
      (q/rect 79 370 441 9)
      (q/rect-mode :center))
  (assoc state :board (new-board)))

(defn get-winner? [board]
  (letfn [(check-winner [rc]
            (when (apply = rc)
              (first rc)))]
    (or
      (check-winner (nth board 0))
      (check-winner (nth board 1))
      (check-winner (nth board 2))
      (check-winner (map first board))
      (check-winner (map second board))
      (check-winner (map #(nth % 2) board))
      (check-winner [(get-in board [0 0]) (get-in board [1 1]) (get-in board [2 2])])
      (check-winner [(get-in board [0 2]) (get-in board [1 1]) (get-in board [2 0])]))))

(defn check-winner! [state]
  (log "check-winner!")
  (if-let [winner ({:o "You" :x "The CPU"}
                   (get-winner? (:board state)))]
    (do (jq/html ($ "#ttt-status")
                 (str winner " Won! Starting a new game..."))
        (reset-game! state))
    (if (->> (flatten (:board state))
             (filter nil?)
             count zero?)
      (do (log "It was a tie!")
          (jq/html ($ "#ttt-status")
                   (str "It was a tie! Starting a new game..."))
          (reset-game! state))
      state)))

(defn draw-board
  [{:keys [board regions] :as state}]
  (let [get-rand-rgb (fn [] (take 3 (repeatedly #(q/random 255))))]
    (do
      (q/rect-mode :corner)
      ;;draw border & white background
      (q/background 209 209 210)
      (q/fill 255)
      (q/rect 9 9 582 582)

      ;;draw lines for grid
      (q/fill 0)
      (q/rect 220 79 9 441)
      (q/rect 370 79 9 441)
      (q/rect 79 220 441 9)
      (q/rect 79 370 441 9)
      (q/rect-mode :center))

  ;;draw each tile
  (doseq [r (range 3)
          c (range 3)
          :let [tile [r c]]]
    (cond
      (= :x (get-in board tile))
      (let [[x y] (tile->center regions tile)]
        (do (apply q/fill (get-rand-rgb))
            (q/ellipse x y 70 70)))

      (= :o (get-in board tile))
      (let [[x y] (tile->center regions tile)]
        (do (apply q/fill (get-rand-rgb))
            (q/ellipse x y 100 100)
            (q/fill 255)
            (q/ellipse x y 90 90)))

      :else nil))

  state))

(defn move-player
  [state tile]
  (-> state
      (add-circle tile)
      (draw-board)
      (check-winner!)))

(defn move-cpu
  [state]
  (-> state
      (cpu-move)
      (draw-board)
      (check-winner!)))

;;todo tile -> board-pos?
(defn play-ttt
  [{:keys [regions board] :as state}
   {:keys [x y]}]
  (let [tile (:tile (tile->region regions [x y]))]
    (if (and (not= [nil nil] tile)
             (not (get-in board tile)))
      (-> state
        (move-player tile)
        (move-cpu))
      state)))

(q/defsketch ttt-canvas
  :size [600 600]
  :setup init-board
  :mouse-pressed play-ttt
  :middleware [m/fun-mode])
