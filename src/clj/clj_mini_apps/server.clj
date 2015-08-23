(ns clj-mini-apps.server
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :refer :all]
            [hiccup.def :refer [defhtml]]
            [compojure.core :refer :all]

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

  (route/resources "/")

  (route/not-found "ERROR 404, NOT FOUND"))

(def app
  (handler/site main-routes))
