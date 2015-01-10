(ns clj-mini-apps.tic-tac-toe
  (:require [hiccup.def :refer [defhtml]]))

(defhtml tic-tac-toe []
  [:head
   [:title "Tic Tac Toe"]
   [:link {:rel "stylesheet" :href "/css/splash.css"}]]

  [:body
   [:p {:style "color:white"} "I'm the body, for ttt"]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "/js/cljs.js"}]
   [:script "clj_mini_apps.tic-tac-toe.init();"]])
