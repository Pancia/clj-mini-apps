(ns clj-mini-apps.games
  (:require [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(do
  (jq/bind ($ "#ttt-button") :click
           #(set! (.-location js/window)
                  "http://localhost:3000/games/tic-tac-toe"))
  (jq/bind ($ "#pong-button") :click
<<<<<<< HEAD
           #(set! (.-location js/window)
                  "http://localhost:3000/games/pong"))
  (jq/bind ($ "#lo-button") :click
           #(set! (.-location js/window)
                  "http://localhost:3000/games/lift-off"))
=======
                 #(set! (.-location js/window)
                        "http://localhost:3000/games/pong"))
  (jq/bind ($ "#lo-button") :click
                 #(set! (.-location js/window)
                        "http://localhost:3000/games/lift-off")))
>>>>>>> e18291381ea2f716e9ce6419bc31fe93e7a72f00
  (jq/bind ($ "#tetris-button") :click
           #(set! (.-location js/window)
                  "http://localhost:3000/games/tetris")))
