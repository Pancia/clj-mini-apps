(ns clj-mini-apps.tic-tac-toe
  (:require [quil.core :as q :include-macros true]

            [jayq.core :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "ttt init"))))

(defn setup []
  ;Create background and outline
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
  {:rects []})

(defn draw 
  [{:keys [:rects]}]
  (q/fill 255 0 0)
  (doseq [{:keys [x y w h]} rects]
    (q/rect x y w h))
  {:keys [x y w h]})

(defn add-rect
  [state event]
  (log (q/mouse-x))
  (log (q/mouse-y))
  (q/fill 255 0 0)
  (q/rect (q/mouse-x) (q/mouse-y) 10 10))
  ;(update-in state [:rects]
   ;          conj {:x (q/mouse-x) :y (q/mouse-y) :w 10 :h 10}))

(q/defsketch hello
  :host "canvas"
  :size [600 600]
  :setup setup
  :mouse-pressed add-rect)
