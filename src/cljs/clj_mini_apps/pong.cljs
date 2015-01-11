(ns clj-mini-apps.pong
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "pong init"))))

(def ball-size 15)

(def paddle-width 20)

(def paddle-height 100)

(def speed 5)

(defn init-game []
  (do
    (q/rect-mode :corner)
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
    (q/rect 59 250 paddle-width paddle-height)
    (q/rect 1040 250 paddle-width paddle-height)
    (q/ellipse 550 250 ball-size ball-size))

  (let [deg (rand-nth (concat (range 135 225)
                              (range 0 45)
                              (range 315 359)))]
    {:player {:x 59 :y 250}
     :cpu    {:x 1040 :y 250}
     :ball   {:x 550 :y 250
              :dx (- (* 4 (q/cos (q/radians deg))))
              :dy    (* 4 (q/sin (q/radians deg)))}
     :is-running? false}))

(defn move-player-paddle
  [state direction]
  (let [half-height (/ paddle-height 2)
        player-y (:y (:player state))]
    (case direction
      :down (if (>= (+ half-height player-y speed) 490)
              (assoc-in state [:player :y] (- 489.5 half-height))
              (update-in state [:player :y] + speed))
      :up   (if (<= (- player-y half-height speed) 9)
              (assoc-in state [:player :y] (+ half-height 8.5))
              (update-in state [:player :y] - speed))
      state)))

(defn move-cpu-paddle
  [state]
  (let [half-height (/ paddle-height 2)
        cpu-y (:y (:cpu state))]
    (if (> (:y (state :ball)) cpu-y)
      (if (>= (+ half-height cpu-y speed) 490)
              (assoc-in state [:cpu :y] (- 489.5 half-height))
              (update-in state [:cpu :y] + (/ speed 2)))
      (if (<= (- cpu-y half-height speed) 9)
              (assoc-in state [:cpu :y] (+ half-height 8.5))
              (update-in state [:cpu :y] - (/ speed 2))))))

(defn start-game
  [state]
  (do (jq/html ($ "#pong-status") ""))
  (if (not (#{:x-dir :y-dir} (keys (:ball state))))
    (as-> state state
      (assoc-in state [:ball :x-dir] (rand-nth [+ -]))
      (assoc-in state [:ball :y-dir] (rand-nth [+ -]))
      (update-ball state))
    state))

(defn update-ball
  [state]
  (if (:is-running? state)
    (as-> state state
      (update-in state [:ball :y] (:y-dir (:ball state)) (:dy (:ball state)))
      (update-in state [:ball :x] (:x-dir (:ball state)) (:dx (:ball state))))
    state))

(defn handle-input
  [pressed state]
  (log (str "k = " (when pressed :up)))
  (condp = (q/key-as-keyword)
    :w (assoc-in state [:player :dir] (when pressed :up))
    :s (assoc-in state [:player :dir] (when pressed :down))
    (keyword " ") (if pressed
                    (-> state
                        (update-in [:is-running?] not)
                        (start-game))
                    state)
    :r (init-game)
    state))

(defn on-collision
  [state]
  (let [ball-x (:x (:ball state))
        ball-y (:y (:ball state))
        top-wall (+ 9 7.5 2)
        btm-wall (- 490 7.5 2)
        not-dir #({+ -, - +} %)]
    (cond
      ;;check w/ top/btm walls
      (not (and (> ball-y top-wall)
                (< ball-y btm-wall)))
      (update-in state [:ball :y-dir] not-dir)

      ;;check w/ player paddle
      (and (< ball-x (+ (:x (:player state)) paddle-width))
           (> ball-x (- (:x (:player state)) paddle-width))
           (< ball-y (+ (:y (:player state)) 50))
           (> ball-y (- (:y (:player state)) 50)))
      (as-> state state
        (update-in state [:ball :x-dir] not-dir)
        (update-in state [:ball :dy] * 1.05)
        (update-in state [:ball :dx] * 1.05))

      ;;check w/ cpu paddle
      (and (> ball-x (- (:x (:cpu state)) paddle-width))
           (< ball-x (+ (:x (:cpu state)) paddle-width))
           (< ball-y (+ (:y (:cpu state)) 50))
           (> ball-y (- (:y (:cpu state)) 50)))
      (as-> state state
        (update-in state [:ball :x-dir] not-dir)
        (update-in state [:ball :dy] * 1.05)
        (update-in state [:ball :dx] * 1.05))

      :else state)))

(defn draw-game
  [state]
  (do
    (q/rect-mode :corner)
    (q/background 209 209 210)
    (q/fill 255)
    (q/rect 9 9 1082 482)
    ;Create paddles and ball
    (q/fill 0)
    (q/rect-mode :center))

  (q/rect ((state :player) :x)
          ((state :player) :y) paddle-width paddle-height)
  (q/rect ((state :cpu) :x)
          ((state :cpu) :y) paddle-width paddle-height)
  (q/ellipse ((state :ball) :x)
             ((state :ball) :y) ball-size ball-size))

(defn get-winner
  [state]
  (let [ball-x (:x (state :ball))]
    (if (< ball-x 0)
      "The CPU"
      (if (> ball-x 1099)
        "You"))))

(defn check-win
  [state]
  (if-let [winner (get-winner state)]
    (do (jq/html ($ "#pong-status")
                 (str winner " Won! Press space to start a new game..."))
    (init-game))
    state))

(defn update-game
  [state]
  (if (:is-running? state)
    (-> state
        (update-ball)
        (move-player-paddle (:dir (state :player)))
        (move-cpu-paddle)
        (on-collision)
        (check-win))
    state))

(q/defsketch pong-canvas
  :size [1100 500]
  :setup init-game
  :key-pressed (partial handle-input true)
  :draw draw-game
  :key-released (partial handle-input false)
  :update update-game
  :middleware [m/fun-mode])
