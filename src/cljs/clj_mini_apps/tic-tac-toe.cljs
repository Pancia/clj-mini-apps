(ns clj-mini-apps.tic-tac-toe
  (:require [quil.core :as q :include-macros true]

            [jayq.core :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "ttt init"))))

(defn draw []
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
  (q/rect 79 370 441 9))

(q/defsketch hello
  :draw draw
  :host "canvas"
  :size [600 600])
