(ns clj-mini-apps.server
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :refer :all]
            [hiccup.def :refer [defhtml]]
            [compojure.core :refer :all]

            [clj-mini-apps.splash :refer [splash]]
            [clj-mini-apps.tic-tac-toe :refer [tic-tac-toe]]
            [clj-mini-apps.games :refer [games]]))

(defroutes main-routes
  (GET "/" []
       (splash))

  (GET "/games/tic-tac-toe" []
       (tic-tac-toe))

  (GET "/games" []
    (games))

  (route/resources "/")

  (route/not-found "ERROR 404, NOT FOUND"))

(def app
  (handler/site main-routes))
