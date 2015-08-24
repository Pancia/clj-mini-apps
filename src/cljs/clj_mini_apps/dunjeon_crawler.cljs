(ns clj-mini-apps.dunjeon-crawler
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "dunjeon-crawler init"))))

(defn init-game []
  5)

(defn draw-game []
  3)

(defn update-game []
  6)

(defn handle-input []
  1)

(q/defsketch brutish-pong-canvas
  :size [1100 500]
  :setup init-game
  :key-pressed (partial handle-input true)
  :draw draw-game
  :key-released (partial handle-input false)
  :update update-game
  :middleware [m/fun-mode])
