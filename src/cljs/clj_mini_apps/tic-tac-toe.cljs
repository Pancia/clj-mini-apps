(ns clj-mini-apps.tic-tac-toe
  (:require [quil.core :as q :include-macros true]

            [jayq.core :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "ttt init"))))

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
  (q/rect-mode :center))

(defn new-board []
  [[nil nil nil]
   [nil nil nil]
   [nil nil nil]])

(def board
  (atom
    (new-board)))

(def cells
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

(defn is-in-cell?
  [{:keys [x y w h]} [mx my]]
  (and (> mx x)
       (< mx (+ x w))
       (> my y)
       (< my (+ y h))))

(defn mouse->cell
  [[mx my]]
  (->> cells
       (filter #(is-in-cell? % [mx my]))
       (first)))

(defn add-circle!
  [[mx my] [tx ty]]
  (let [[x y] (:center (mouse->cell [mx my]))]
    (swap! board #(assoc-in % [tx ty] :o))
    (when (every? identity [x y])
      (q/fill (q/random 255) (q/random 255) (q/random 255))
      (q/ellipse x y 100 100)
      (q/fill 255)
      (q/ellipse x y 90 90))))

(defn tile->center
  [[tx ty]]
  (->> cells
       (map (fn [{:keys [center tile]}]
              (when (= [tx ty] tile)
                center)))
       (filter identity)
       (first)))

(defn cpu-move! []
  (let [get-rand-tile (fn [] [(rand-int 3) (rand-int 3)])]
    (loop [[tx ty] (get-rand-tile)]
      (if (not (get-in @board [tx ty]))
        (let [[cpu-x cpu-y] (tile->center [tx ty])]
          (do (swap! board #(assoc-in % [tx ty] :x))
              (q/fill (q/random 255) (q/random 255) (q/random 255))
              (q/ellipse cpu-x cpu-y 70 70)))
        (recur (get-rand-tile))))))

(defn winner?! [board]
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

(defn clear-canvas! []
  (q/rect-mode :corner)
  (q/background 209 209 210)
  (q/fill 255)
  (q/rect 9 9 582 582)
  (q/fill 0)
  (q/rect 220 79 9 441)
  (q/rect 370 79 9 441)
  (q/rect 79 220 441 9)
  (q/rect 79 370 441 9)
  (q/rect-mode :center))

(defn check-winner! []
  (if-let [winner ({:o "You" :x "The CPU"} (winner?! @board))]
    (do (log (str winner " Won!"))
        (log "Starting A New Game")
        (reset! board (new-board))
        (clear-canvas!))
    (when (->> (flatten @board)
               (filter nil?)
               count zero?)
      (do (log "It was a tie!")
          (log "Starting A New Game")
          (reset! board (new-board))
          (clear-canvas!)))))

(defn move-player
  [[mx my] [tx ty]]
  (do (add-circle! [mx my] [tx ty])
      (check-winner!)))

(defn move-cpu []
  (do (cpu-move!)
      (check-winner!)))

(defn play-ttt! []
  (let [[mx my] [(q/mouse-x) (q/mouse-y)]
        [tx ty] (:tile (mouse->cell [mx my]))]
    (if (and (every? identity [tx ty])
             (not (get-in @board [tx ty])))
      (do (move-player [mx my] [tx ty])
          (move-cpu)))))

(q/defsketch hello
  :host "canvas"
  :size [600 600]
  :setup init-board
  :mouse-pressed play-ttt!)
