(ns clj-mini-apps.server
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :refer :all]
            [hiccup.def :refer [defhtml]]
            [compojure.core :refer :all]
            [org.httpkit.server :refer :all]
            [taoensso.sente :as sente]
            [taoensso.sente.server-adapters.http-kit :refer [sente-web-server-adapter]]
            [clojure.core.async :as a :refer [go <!]]

            [clj-mini-apps.template        :refer [template]]
            [clj-mini-apps.splash          :refer [splash]]
            [clj-mini-apps.pong            :refer [pong]]
            [clj-mini-apps.tic-tac-toe     :refer [tic-tac-toe]]
            [clj-mini-apps.lift-off        :refer [lift-off]]
            [clj-mini-apps.dunjeon-crawler :refer [dunjeon-crawler]]
            [clj-mini-apps.games           :refer [games]]
            [clj-mini-apps.blog            :refer [blog]]
            [clj-mini-apps.gallery         :refer [gallery]]
            [clj-mini-apps.about-us        :refer [about-us]]
            [clj-mini-apps.brutish-pong    :refer [brutish-pong]]
            [clj-mini-apps.tetris          :refer [tetris]]))

(let [{:keys [ch-recv send-fn ajax-post-fn ajax-get-or-ws-handshake-fn
              connected-uids]}
      (sente/make-channel-socket! sente-web-server-adapter {})]
  (def ring-ajax-post                ajax-post-fn)
  (def ring-ajax-get-or-ws-handshake ajax-get-or-ws-handshake-fn)
  (def ch-chsk                       ch-recv) ; ChannelSocket's receive channel
  (def chsk-send!                    send-fn) ; ChannelSocket's send API fn
  (def connected-uids                connected-uids) ; Watchable, read-only atom
  )

(defroutes main-routes
  (GET "/" []
       (template splash))

  (GET "/gallery" []
       (template gallery))

  (GET "/about-us" []
       (template about-us))

  (GET "/blog" []
       (template blog))

  (GET "/gallery" []
       (gallery))

  (GET "/about-us" []
       (about-us))

  (GET "/blog" []
       (blog))

  (GET "/games" []
       (template games))

  (GET "/games/tic-tac-toe" []
       (template tic-tac-toe))

  (GET "/games/dunjeon-crawler" []
       (template dunjeon-crawler))

  (GET "/games/dunjeon-crawler" []
       (dunjeon-crawler))

  (GET "/games/pong" []
       (template pong))

  (GET "/games/brutish-pong" []
       (template brutish-pong))

  (GET "/games/lift-off" []
       (template lift-off))

  (GET "/games/lift-off" []
       (lift-off))

  (GET "/games/tetris" []
       (template tetris))

  (GET  "/chsk" req (ring-ajax-get-or-ws-handshake req))
  (POST "/chsk" req (ring-ajax-post                req))

  (route/resources "/")

  (route/not-found "ERROR 404, NOT FOUND"))

(defmulti event-msg-handler :id)
(defn event-msg-handler* [{:as ev-msg :keys [id ?data event]}]
  (println "Event: " event)
  (event-msg-handler ev-msg))

(defmethod event-msg-handler :default
  [{:as ev-msg :keys [event id ?data ring-req ?reply-fn send-fn]}]
  (let [session (:session ring-req)
        uid     (:uid     session)]
    (println "Unhandled event: " event)
    (when ?reply-fn
      (?reply-fn {:umatched-event-as-echoed-from-the-server event}))))

(defn start-router! []
  (println "start-router!")
  (sente/start-chsk-router! ch-chsk event-msg-handler*))

(def app
  (do (start-router!)
      (-> main-routes
          (handler/site)
          (run-server {:port 8080}))))
