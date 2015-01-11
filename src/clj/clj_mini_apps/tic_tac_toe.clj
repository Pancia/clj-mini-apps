(ns clj-mini-apps.tic-tac-toe
  (:require [hiccup.def :refer [defhtml]]))

(defhtml tic-tac-toe []
  [:head
   [:title "Tic Tac Toe"]
   [:link {:rel "stylesheet" :href "/css/bootstrap.min.css"}]
   [:link {:rel "stylesheet" :href "/css/splash.css"}]]

  [:body
   [:div
      [:ul {:class "nav nav-pills"}
        [:li {:role "presentation"}
          [:a {:href "/"} "Home"]]
        [:li {:role "presentation"}
          [:a {:href "/games"} "Games"]]
        [:li {:role "presentation"}
          [:a {:href ""} "Blog"]]
        [:li {:role "presentation"}
          [:a {:href ""} "Gallery"]]
        [:li {:role "presentation"}
          [:a {:href ""} "About Us"]]]]
   [:h1 {:style "text-align:center"} "Tic Tac Toe"]
   [:canvas {:id "ttt-canvas"
             :style "margin-left:auto;
                     margin-right:auto;
                     display:block"}]
   [:h3 {:id "ttt-status"
        :style "text-align:center"} ""]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "/js/cljs.js"}]
   [:script {:src "/js/bootstrap.min.js"}]
   [:script "clj_mini_apps.tic_tac_toe.init();"]])
