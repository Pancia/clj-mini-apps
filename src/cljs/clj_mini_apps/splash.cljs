(ns clj-mini-apps.splash
  (:require [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (if (and js/document (.-getElementById js/document))
    (do (log "splash init")
        (jq/bind ($ "#clickme") :click
                 #(set! (.-location js/window)
                        "http://localhost:3000/games")))
    (do (log "failed to init splash"))))
