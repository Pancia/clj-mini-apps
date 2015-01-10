(ns clj-mini-apps.splash
  (:require [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]
            [ajax.core :refer [GET]]))

(defn ^:export init []
  (if (and js/document (.-getElementById js/document))
    (do (log "spash init")
        (jq/bind ($ "#clickme") :click
                 (fn [e] (log "my butt"))))
    (do (log "failed to init splash"))))
