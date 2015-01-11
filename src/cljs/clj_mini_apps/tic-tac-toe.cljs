(ns clj-mini-apps.tic-tac-toe
  (:require [quil.core :as q :include-macros true]

            [jayq.core :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "ttt init"))))

(defn draw []
  (q/background 128 3 235)
  (q/fill 128 0 128)
  (q/ellipse 56 46 55  55))

(q/defsketch hello
  :draw draw
  :host "canvas"
  :size [300 300])
