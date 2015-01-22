(ns clj-mini-apps.lift-off
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "lift-off init"))))

(def ship-width 80)

(def ship-height 80)

(def speed 15)

(def width 800)

(def height 500)

(defn draw-ship [x y]
  (do
    (q/fill 54 66 69)
    (q/ellipse x y ship-width (/ ship-height 2))
    (q/fill 75 91 94)
    (q/ellipse x (- y 5) ship-width (/ ship-height 2))
    (q/fill 111 131 135)
    (q/ellipse x (- y (/ ship-height 6)) (/ ship-width 2) (/ ship-height 4))
    (q/ellipse x (- y (/ ship-height 4)) (/ ship-width 2) (/ ship-height 3))))

(defn init-game []
  (do
    (q/rect-mode :corner)
    ;Create background and outline
    (q/frame-rate 60)
    (q/smooth)
    (q/background 209 209 210)
    (q/fill 71 194 222)
    (q/no-stroke)
    (q/rect 9 9 (- width 18) (- height 18))
    ;Create ship
    (draw-ship 60 (/ height 2)))

  (let [deg (rand-nth (concat (range 135 225)
                              (range 0 45)
                              (range 315 359)))]
    {:ship {:x 60 :y (/ height 2)}
     :is-running? false}
     :obstacles []))

(q/defsketch lo-canvas
  :size [width height]
  :setup init-game
  ;;:key-pressed (partial handle-input true)
  ;;:draw draw-game
  ;;:key-released (partial handle-input false)
  ;;:update update-game
  :middleware [m/fun-mode])
