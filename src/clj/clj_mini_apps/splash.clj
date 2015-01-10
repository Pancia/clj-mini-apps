(ns clj-mini-apps.splash
  (:require [hiccup.def :refer [defhtml]]))

(defhtml splash []
  [:head
   [:meta {:http-equiv "Content-type"
           :content "text/html; charset=utf-8"}]
   [:title "Clojure Test Website"]
   [:link {:rel "stylesheet" :href "/css/splash.css"}]]

  [:body
   [:p {:style "color:white"} "I'm the body!"]
   [:button {:id "clickme"
             :type "button"} "Click me!"]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "/js/cljs.js"}]
   [:script "clj_mini_apps.splash.init();"]])
