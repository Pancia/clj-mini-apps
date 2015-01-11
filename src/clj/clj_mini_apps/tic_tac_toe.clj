(ns clj-mini-apps.tic-tac-toe
  (:require [hiccup.def :refer [defhtml]]))

(defhtml tic-tac-toe []
  [:head
   [:title "Tic Tac Toe"]
   [:link {:rel "stylesheet" :href "/css/splash.css"}]]

  [:body
   [:h1 {:style "color:white;
                text-align:center"} "Tick Tac Toe"
    [:canvas {:id "canvas"
              :width 600
              :height 600
              :style "margin-left:auto;
                      margin-right:auto;
                      display:block"}]]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "/js/cljs.js"}]
   [:script "clj_mini_apps.tic_tac_toe.init();"]])
