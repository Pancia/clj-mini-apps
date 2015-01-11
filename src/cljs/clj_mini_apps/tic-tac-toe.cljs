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

(def board
  (atom
    [[nil nil nil]
     [nil nil nil]
     [nil nil nil]]))

(def cells
  (for [x [79 229 379]
        y [79 229 379]
        :let [[bx by] [(+ x 70)
                       (+ y 70)]
              [row col] [(dec (quot bx 141))
                         (dec (quot by 141))]]]
    {:x x :y y
     :w 141 :h 141
     :center [bx by] :tile [row col]}))

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

(defn add-circle
  [mx my]
  (let [[x y] (:center (mouse->cell [mx my]))]
    (when (every? identity [x y])
      (q/fill (q/random 255) (q/random 255) (q/random 255))
      (q/ellipse x y 100 100)
      (q/fill 255)
      (q/ellipse x y 90 90))))

(defn play-ttt []
  (let [[mx my] [(q/mouse-x) (q/mouse-y)]
        [tx ty] (:tile (mouse->cell [mx my]))]
    (if true
      (add-circle mx my))))

(q/defsketch hello
  :host "canvas"
  :size [600 600]
  :setup init-board
  :mouse-pressed play-ttt)
