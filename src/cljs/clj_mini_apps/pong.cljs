(ns clj-mini-apps.pong
	(:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [jayq.core :refer [$]]
	          [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "pong init"))))

(def ball-size 15)

(def paddle-width 20)

(def speed 10)

(defn init-game []
  (log "init-game")
  ;Create background and outline
  (q/frame-rate 1)
  (q/smooth)
  (q/background 209 209 210)
  (q/fill 255)
  (q/no-stroke)
  (q/rect 9 9 1082 482)
  ;Create paddles and ball
  (q/fill 0)
  (q/rect-mode :center)
  (q/rect 59 250 20 100)
  (q/rect 1040 250 20 100)
  (q/ellipse 550 250 ball-size ball-size)
  {:player {:x 59 :y 250} :computer {:x 1040 :y 250} :ball {:x 550 :y 250}})

(defn handle-input [state]
  (log "handle-input")
  (log (q/key-as-keyword))
  (condp = (q/key-as-keyword)
    :w (move-player-paddle state :up)
    :s (move-player-paddle state :down)
    (keyword " ") (start-game state)
    state)
  state)

(defn move-player-paddle 
  [state direction]
  (log "move-player-paddle")
  (log direction)
  (if (= direction :down)
    (update-in state [:player :y] + speed)
    (update-in state [:player :y] - speed)))

(defn start-game
  [state]
  (log "start-game"))

(defn update-ball
  [state]
  (log "update-ball"))

(defn draw-game
  [state]
  (log "draw-game")
  (q/rect-mode :corner)
  (q/fill 255)
  (q/rect 9 9 1082 482)
  ;Create paddles and ball
  (q/fill 0)
  (q/rect-mode :center)
  (q/rect (-> state :player :x) (-> state :player :y) 20 100)
  (q/rect (-> state :computer :x) (-> state :computer :y) 20 100)
  (q/ellipse (-> state :ball :x) (-> state :ball :y) ball-size ball-size)
  (-> state (handle-input)))

(q/defsketch pong-canvas
  :size [1100 500]
  :setup init-game
  :key-pressed handle-input
  :draw draw-game
  :middleware [m/fun-mode])