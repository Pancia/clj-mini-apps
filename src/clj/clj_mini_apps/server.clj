(ns clj-mini-apps.server
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as response]
            [hiccup.core :refer :all]
            [compojure.core :refer :all]))

(defn splash-page []
  (html
    [:head
     [:meta {:http-equiv "Content-type"
             :content "text/html; charset=utf-8"}]
     [:title "Clojure Test Website"]
     ;[:link {:rel "stylesheet" :href "/css/avatar.css"}]
     ]

     [:body
      [:p "I'm the body!"]
      [:button {:id "clickme"
                :type "button"} "Click me!"]
      [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
      [:script {:src "/js/cljs.js"}]
      [:script "clj_mini_apps.splash.init();"]]))

(defroutes main-routes
  (GET "/" []
       (splash-page))

  (route/resources "/")

  (route/not-found "ERROR 404, NOT FOUND"))

(def app
  (handler/site main-routes))
