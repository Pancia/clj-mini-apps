(ns clj-mini-apps.pong
	(:require [hiccup.def :refer [defhtml]]))

(defhtml pong []
  [:head
   [:title "Pong"]
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
   [:h1 {:style "text-align:center"} "Pong"]
   [:canvas {:id "pong-canvas"
             :style "margin-left:auto;
                     margin-right:auto;
                     display:block"}]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "/js/cljs.js"}]
   [:script {:src "/js/bootstrap.min.js"}]
   [:script "clj_mini_apps.pong.init();"]])