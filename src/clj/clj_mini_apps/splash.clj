(ns clj-mini-apps.splash
  (:require [hiccup.def :refer [defhtml]]))

(defhtml splash []
  [:head
   [:meta {:http-equiv "Content-type"
           :content "text/html; charset=utf-8"}]
   [:title "Clojure Test Website"]
   [:link {:rel "stylesheet" :href "/css/bootstrap.min.css"}]
   [:link {:rel "stylesheet" :href "/css/splash.css"}]]

  [:body
    [:div
      [:ul {:class "nav nav-pills"}
        [:li {:class "active"
              :role "presentation"}
          [:a {:href ""} "Home"]]
        [:li {:role "presentation"}
          [:a {:href "games"} "Games"]]
        [:li {:role "presentation"}
          [:a {:href ""} "Blog"]]
        [:li {:role "presentation"}
          [:a {:href ""} "Gallery"]]
        [:li {:role "presentation"}
          [:a {:href ""} "About Us"]]]]
    [:p {:style "color:black"} "I'm the body!"]
    [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
    [:script {:src "/js/bootstrap.min.js"}]
    [:script {:src "/js/cljs.js"}]
    [:script "clj_mini_apps.splash.init();"]])
