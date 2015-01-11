(ns clj-mini-apps.brutish-pong
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "brutish-pong init"))))


