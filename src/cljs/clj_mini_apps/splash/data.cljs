(ns clj-mini-apps.splash.data
  (:require [jayq.util :refer [log]]
            [cljs.core.async :as a :refer [>! <! chan]]
            [taoensso.sente :as sente :refer [make-channel-socket!]])
  (:require-macros [cljs.core.async.macros :as am :refer [go]]))

(let [{:keys [chsk ch-recv send-fn state]}
      (make-channel-socket! "/chsk"
                            {:type :auto ; e/o #{:auto :ajax :ws}
                             })]
  (def chsk       chsk)
  (def ch-chsk    ch-recv) ; ChannelSocket's receive channel
  (def chsk-send! send-fn) ; ChannelSocket's send API fn
  (def chsk-state state)   ; Watchable, read-only atom
  )

(defn test [state val]
  (log "consuming " val " with state " state)
  state)

(defn add-article [state {:keys [title body]}]
  (chsk-send! [:splash.data/add-article {:title title}]
              log)
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

(defmulti event-msg-handler :id)
;; Wrap for logging, catching, etc.:
(defn     event-msg-handler* [{:as ev-msg :keys [id ?data event]}]
  (log "Event: " event)
  (event-msg-handler ev-msg))

(defmethod event-msg-handler :default
  [{:as ev-msg :keys [event]}]
  (log "Unhandled event: " event))

(defmethod event-msg-handler :chsk/state
  [{:as ev-msg :keys [?data]}]
  (if (= ?data {:first-open? true})
    (log "Channel socket successfully established!")
    (log "Channel socket state change: " ?data)))

(defmethod event-msg-handler :chsk/recv
  [{:as ev-msg :keys [?data]}]
  (log "Push event from server: " ?data))

(defmethod event-msg-handler :chsk/handshake
  [{:as ev-msg :keys [?data]}]
  (let [[?uid ?csrf-token ?handshake-data] ?data]
    (log "Handshake: " ?data)))

(defn start-router! []
  (sente/start-chsk-router! ch-chsk event-msg-handler*))

(defn start! []
  (start-router!))

(start!)
