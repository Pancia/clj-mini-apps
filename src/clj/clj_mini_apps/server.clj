(ns clj-mini-apps.server
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :refer :all]
            [hiccup.def :refer [defhtml]]
            [compojure.core :refer :all]

            [clj-mini-apps.splash :refer :all]
            [clj-mini-apps.tic-tac-toe :refer :all]))

(defroutes main-routes
  (GET "/" []
       (splash))

  (GET "/games/tic-tac-toe"  []
       (tic-tac-toe))

  (route/resources "/")

  (route/not-found "ERROR 404, NOT FOUND"))

(def app
  (handler/site main-routes))
