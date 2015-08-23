(ns clj-mini-apps.server
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :refer :all]
            [hiccup.def :refer [defhtml]]
            [compojure.core :refer :all]

            [clj-mini-apps.splash       :refer [splash]]
            [clj-mini-apps.pong         :refer [pong]]
            [clj-mini-apps.tic-tac-toe  :refer [tic-tac-toe]]
            [clj-mini-apps.games        :refer [games]]
            [clj-mini-apps.brutish-pong :refer [brutish-pong]]
            [clj-mini-apps.tetris       :refer [tetris]]
            [clj-mini-apps.template     :refer [template]]))

(defroutes main-routes
  (GET "/" []
       (template splash))

  (GET "/games" []
       (template games))

  (GET "/games/tic-tac-toe" []
       (template tic-tac-toe))

  (GET "/games/pong" []
       (template pong))

  (GET "/games/brutish-pong" []
       (template brutish-pong))

  (GET "/games/tetris" []
       (template tetris))

  (route/resources "/")

  (route/not-found "ERROR 404, NOT FOUND"))

(def app
  (handler/site main-routes))
