(ns clj-mini-apps.games
	(:require [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(do 
  (jq/bind ($ "#ttt-button") :click
                 #(set! (.-location js/window)
                        "http://localhost:3000/games/tic-tac-toe"))
  (jq/bind ($ "#pong-button") :click
                 #(set! (.-location js/window)
                        "http://localhost:3000/games/pong")))