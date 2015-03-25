(ns clj-mini-apps.pong
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "pong init"))))

(def ball-size 15)

;; Factor at which speed is updated when contact is made with paddle
(def speed-factor 1.05)

(def paddle-width 20)

(def paddle-height 100)

(def screen-width 1100)

(def screen-height 500)

(def paddle-to-wall-dist 100)

(def outline-size 9)

(def speed 5)

(defn init-game []
  (let [half-screen-height (/ screen-height 2)
        half-screen-width (/ screen-width 2)
        player-x (+ paddle-to-wall-dist outline-size)
        cpu-x (- screen-width outline-size paddle-to-wall-dist)]
    (do
      (q/rect-mode :corner)
      ;Create background and outline
      (q/frame-rate 60)
      (q/smooth)
      (q/background 209 209 210)
      (q/fill 255)
      (q/no-stroke)
      (q/rect outline-size outline-size (- screen-width (* 2 outline-size)) 
                                        (- screen-height (* 2 outline-size)))
      ;Create paddles and ball
      (q/fill 0)
      (q/rect-mode :center)
      (q/rect player-x half-screen-height paddle-width paddle-height)
      (q/rect cpu-x half-screen-height paddle-width paddle-height)
      (q/ellipse half-screen-width half-screen-height ball-size ball-size))
  
    (let [deg (rand-nth (concat (range 135 225)
                                (range 0 45)
                                (range 315 359)))]
      {:player {:x player-x :y half-screen-height}
       :cpu    {:x cpu-x :y half-screen-height}
       :ball   {:x half-screen-width :y half-screen-height
                :dx (- (* 4 (q/cos (q/radians deg))))
                :dy    (* 4 (q/sin (q/radians deg)))}
       :is-running? false})))

(defn move-player-paddle
  [state direction]
  (let [half-height (/ paddle-height 2)
        player-y (:y (:player state))]
    (case direction
      :down (if (>= (+ half-height player-y speed) (- screen-height outline-size))
              (assoc-in state [:player :y] 
                (- screen-height outline-size half-height))
              (update-in state [:player :y] + speed))
      :up   (if (<= (- player-y half-height speed) outline-size)
              (assoc-in state [:player :y] (+ half-height outline-size))
              (update-in state [:player :y] - speed))
      state)))

(defn move-cpu-paddle
  [state]
  (let [half-height (/ paddle-height 2)
        cpu-y (:y (:cpu state))]
    (if (> (:y (state :ball)) cpu-y)
      (if (>= (+ half-height cpu-y speed) (- screen-height outline-size))
              (assoc-in state [:cpu :y] 
                (- screen-height outline-size half-height))
              (update-in state [:cpu :y] + (/ speed 2)))
      (if (<= (- cpu-y half-height speed) outline-size)
              (assoc-in state [:cpu :y] (+ half-height outline-size))
              (update-in state [:cpu :y] - (/ speed 2))))))

(defn update-ball
  [state]
  (let [x-fn (:x-dir (:ball state))
        y-fn (:y-dir (:ball state))]
    (if (:is-running? state)
      (as-> state state
        (update-in state [:ball :y] y-fn (:dy (:ball state)))
        (update-in state [:ball :x] x-fn (:dx (:ball state))))
      state)))

(defn start-game
  [state]
  (do (jq/html ($ "#pong-status") ""))
  (if (not (#{:x-dir :y-dir} (keys (:ball state))))
    (as-> state state
      (assoc-in state [:ball :x-dir] (rand-nth [+ -]))
      (assoc-in state [:ball :y-dir] (rand-nth [+ -]))
      (update-ball state))
    state))

(defn handle-input
  [pressed state]
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
        ball-rad (/ ball-size 2)
        offset (if (neg? (* (:dx (:ball state)) .9))
                   (- 0 (* (:dx (:ball state)) .9))
                   (* (:dx (:ball state)) .9))
        half-height (/ paddle-height 2)
        half-width (/ paddle-width 2)
        player-x (:x (:player state))
        player-y (:y (:player state))
        player-front (+ player-x half-width)
        player-back(- player-x half-width)
        player-top (- player-y half-height)
        player-bottom (+ player-y half-height)
        cpu-x (:x (:cpu state))
        cpu-y (:y (:cpu state))
        cpu-front (- cpu-x half-width)
        cpu-back(+ cpu-x half-width)
        cpu-top (- cpu-y half-height)
        cpu-bottom (+ cpu-y half-height)
        top-wall (+ 9 ball-rad offset)
        btm-wall (- 490 ball-rad offset)
        not-dir #({+ -, - +} %)]
    (cond
      ;;check w/ top/btm walls
      (not (and (> ball-y top-wall)
                (< ball-y btm-wall)))
      (update-in state [:ball :y-dir] not-dir)

      ;;check w/ front of player paddle
      (and (<= ball-x (+ player-front offset))
           (>= ball-x (- player-front offset))
           (<= ball-y player-bottom)
           (>= ball-y player-top))
      (as-> state state
        (update-in state [:ball :x-dir] not-dir)
        (update-in state [:ball :dy] * speed-factor)
        (update-in state [:ball :dx] * speed-factor)
        (assoc-in state [:ball :x] player-front))

      ;;check w/ front of cpu paddle
      (and (>= ball-x (- cpu-front offset))
           (<= ball-x (+ cpu-front offset))
           (<= ball-y cpu-bottom)
           (>= ball-y cpu-top))
      (as-> state state
        (update-in state [:ball :x-dir] not-dir)
        (update-in state [:ball :dy] * speed-factor)
        (update-in state [:ball :dx] * speed-factor)
        (assoc-in state [:ball :x] cpu-front))

      :else state)))

(defn draw-game
  [state]
  (do
    (q/rect-mode :corner)
    (q/background 209 209 210)
    (q/fill 255)
    (q/rect outline-size outline-size 
      (- screen-width (* 2 outline-size)) (- screen-height (* 2 outline-size)))
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
    (if (< ball-x (+ outline-size (/ ball-size 2)))
      "The CPU"
      (if (> ball-x (- screen-width outline-size (/ ball-size 2)))
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
        (move-player-paddle (:dir (state :player)))
        (move-cpu-paddle)
        (update-ball)
        (on-collision)
        (check-win))
    state)) 

(defn init-menu-screen []
  (do
    (q/rect-mode :corner)
    (q/no-stroke)
    (q/smooth)
    (q/background 209 209 210)
    (q/fill 255)
    (q/text-font (q/load-font "/fonts/SimplySquare.ttf"))
    (q/rect outline-size outline-size 
      (- screen-width (* 2 outline-size)) (- screen-height (* 2 outline-size)))
    (q/text "Pong", (/ screen-width 2), (/ screen-height 4))))

(q/defsketch pong-canvas
  :size [screen-width screen-height]
  :setup init-menu-screen
  ;;:key-pressed (partial handle-input true)
  ;;:draw draw-game
  ;;:key-released (partial handle-input false)
  ;;:update update-game
  :middleware [m/fun-mode])
