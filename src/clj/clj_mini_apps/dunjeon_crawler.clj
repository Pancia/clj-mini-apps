(ns clj-mini-apps.dunjeon-crawler
	(:require [hiccup.def :refer [defhtml]]))

(defhtml dunjeon-crawler []
  [:head
   [:title "Dunjeon Crawler"]
   [:link {:rel "shortcut icon" :href "/images/game controller logo small.png"}]
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
          [:a {:href "/blog"} "Blog"]]
        [:li {:role "presentation"}
          [:a {:href "/gallery"} "Gallery"]]
        [:li {:role "presentation"}
          [:a {:href "/about-us"} "About Us"]]]]
   [:h1 {:style "text-align:center"} "Dunjeon Crawler"]
   [:applet {:code "rouje_like.core.desktop_launcher.class"
             :archive "/dunjeon-crawler"
             :width 200
             :height 200}]
   [:h3 {:id "dunjeon-crawler-status"
        :style "text-align:center"} ""]
   [:h4 {:style "text-align:center"}
        "Try to keep the ball from getting past you! You are the margin-left 
        paddle."]
   [:h4 {:style "text-align:center"}
        "CONTROLS:" [:br {}]
        "w - Move paddle up" [:br {}]
        "s - Move paddle down" [:br {}]
        "r - restarts game" [:br {}]
        "space - starts and pauses game \n" [:br {}]]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "/js/cljs.js"}]
   [:script {:src "/js/bootstrap.min.js"}]
   [:script "clj_mini_apps.dunjeon-crawler.init();"]])