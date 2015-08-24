(ns clj-mini-apps.splash.data
  (:require [jayq.util :refer [log]]))

(defn test [state val]
  (log "consuming " val " with state " state)
  state)

(defn add-article [state {:keys [title body]}]
  (update state :articles conj {:title title
                                :body body
                                :author {:name "Anthony"
                                         :email "anthony@test.com"}}))

(defn init []
  {:articles [{:title "Why core.async is awesome"
               :body "..."
               :author {:name "Luke VanderHart"
                        :email "luke@example.com"}}
              {:title "Programming: you're doing it completely wrong"
               :body "..."
               :author {:name "Rich Hickey"
                        :email "rich@example.com"}}]})
