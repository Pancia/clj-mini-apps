(ns clj-mini-apps.splash
  (:require [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (if (and js/document (.-getElementById js/document))
    (do (log "splash init"))
    (do (log "failed to init splash"))))
