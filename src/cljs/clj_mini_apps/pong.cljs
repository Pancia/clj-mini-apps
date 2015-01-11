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

(def speed 7)

(def ball-speed 2)

(defn init-game []
  (log "init-game")
  ;Create background and outline
  (q/frame-rate 60)
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

(defn move-player-paddle 
  [state direction]
  ;(log "move-player-paddle")
  (if (= direction :down)
    (update-in state [:player :y] + speed)
    (update-in state [:player :y] - speed)))

(defn start-game
  [state]
  ;(log "start-game")
  (if (= 2 (count (:ball state)))
    (as-> state state
      (assoc-in state [:ball :x-dir] (rand-nth [+ -]))
      (assoc-in state [:ball :y-dir] (rand-nth [+ -]))  
      (update-ball state))
    state))

(defn update-ball
  [state]
  ;(log "update-ball")
  (if (= 4 (count (state :ball)))
    (as-> state state
      (update-in state [:ball :y] (:y-dir (:ball state)) ball-speed)
      (update-in state [:ball :x] (:x-dir (:ball state)) ball-speed))
    state))

(defn handle-input [state]
  ;(log "handle-input")
  (condp = (q/key-as-keyword)
    :w (move-player-paddle state :up)
    :s (move-player-paddle state :down)
    (keyword " ") (start-game state)
    state))

#_(defn collision [state]
  (let [ball-x (:x (:ball state))]
       [ball-y (:y (:ball state))]
       [new-y-dir (if (= + (:y-dir (:ball state)))
                      -
                      +)])
  ;Check wall collision
  (if (not (and (> ((:y-dir (state :ball)) ball-speed ball-y) (16.5))
                (< ((:y-dir (state :ball)) ball-speed ball-y) (- 490 7.5))))
    (as-> state state
      (assoc-in state [:ball :y-dir] new-y-dir)
      (if (= new-y-dir +)
        (assoc state [:ball :y] 16.5)
        (assoc state [:ball :y] 490))
      (update-in state [:ball :y] (:y-dir (:ball state)) ball-speed))
    (update-ball state)))

(defn draw-game
  [state]
  ;(log "draw-game")
  (log (:x-dir (:ball state)))
  (q/rect-mode :corner)
  (q/fill 255)
  (q/rect 9 9 1082 482)
  ;Create paddles and ball
  (q/fill 0)
  (q/rect-mode :center)
  (q/rect ((state :player) :x) ((state :player) :y) 20 100)
  (q/rect ((state :computer) :x) ((state :computer) :y) 20 100)
  (q/ellipse ((state :ball) :x) ((state :ball) :y) ball-size ball-size))

(defn update-game
  [state]
  (-> state
    (update-ball)))

(q/defsketch pong-canvas
  :size [1100 500]
  :setup init-game
  :key-pressed handle-input
  :draw draw-game
  :update update-game
  :middleware [m/fun-mode])