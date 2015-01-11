(ns clj-mini-apps.server
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :refer :all]
            [hiccup.def :refer [defhtml]]
            [compojure.core :refer :all]

            [clj-mini-apps.splash :refer [splash]]
            [clj-mini-apps.pong :refer [pong]]
            [clj-mini-apps.tic-tac-toe :refer [tic-tac-toe]]
            [clj-mini-apps.games :refer [games]]
            [clj-mini-apps.brutish-pong :refer [brutish-pong]]))

(defroutes main-routes
  (GET "/" []
       (splash))

  (GET "/games/tic-tac-toe" []
       (tic-tac-toe))

  (GET "/games/brutish-pong" []
       (brutish-pong))

  (GET "/games" []
    (games))

  (GET "/games/pong" []
    (pong))

  (route/resources "/")

  (route/not-found "ERROR 404, NOT FOUND"))

(def app
  (handler/site main-routes))
