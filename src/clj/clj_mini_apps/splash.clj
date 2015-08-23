(ns clj-mini-apps.splash
  (:require [hiccup.def :refer [defhtml]]))

<<<<<<< HEAD
(def splash
  {:head [:title "Welcome | Clojure Mini Apps"]

   :page
   [:div {:class "page splash-page"}
    [:p {:style "color:black"}
     "Welcome to the home page!"]]

   :tail [:script "clj_mini_apps.splash.init();"]})
=======
(defhtml splash []
  [:head
   [:meta {:http-equiv "Content-type"
           :content "text/html; charset=utf-8"}]
   [:title "Welcome | Clojure Mini Apps"]
   [:link {:rel "shortcut icon" :href "/images/game controller logo small.png"}]
   [:link {:rel "stylesheet" :href "/css/bootstrap.min.css"}]
   [:link {:rel "stylesheet" :href "/css/splash.css"}]]

  [:body
    [:div
      [:ul {:class "nav nav-pills"}
        [:li {:class "active"
              :role "presentation"}
          [:a {:href "/"} "Home"]]
        [:li {:role "presentation"}
          [:a {:href "/games"} "Games"]]
        [:li {:role "presentation"}
          [:a {:href "/blog"} "Blog"]]
        [:li {:role "presentation"}
          [:a {:href "/gallery"} "Gallery"]]
        [:li {:role "presentation"}
          [:a {:href "/about-us"} "About Us"]]]]
    [:p {:style "color:black"} "Welcome to the home page!"]
    [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
    [:script {:src "/js/bootstrap.min.js"}]
    [:script {:src "/js/cljs.js"}]
    [:script "clj_mini_apps.splash.init();"]])
>>>>>>> e18291381ea2f716e9ce6419bc31fe93e7a72f00
