<<<<<<< HEAD
(ns clj-mini-apps.lift-off)

(def lift-off
  {:head [:title "Lift-Off | Clojure Mini Apps"]

   :page
   [:div {:class "page lift-off-page"}
    [:h1 {:style "text-align:center"} "Lift-Off"]
    [:canvas {:id "lo-canvas"
              :style "margin-left:auto;
                      margin-right:auto;
                      display:block"}]
    [:h3 {:id "lo-status"
          :style "text-align:center"} ""]
    [:h4 {:style "text-align:center"}
     "Press the up arrow to make your ship go up and
      avoid the obstacles."]
    [:h4 {:style "text-align:center"}
     "CONTROLS:" [:br {}]
     "Up - Move ship up" [:br {}]
     "space - starts and pauses game \n" [:br {}]]]

   :tail [:script "clj_mini_apps.pong.init();"]})
=======
(ns clj-mini-apps.lift-off
	(:require [hiccup.def :refer [defhtml]]))

(defhtml lift-off []
  [:head
   [:title "Lift-Off | Clojure Mini Apps"]
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
   [:h1 {:style "text-align:center"} "Lift-Off"]
   [:canvas {:id "lo-canvas"
             :style "margin-left:auto;
                     margin-right:auto;
                     display:block"}]
   [:h3 {:id "lo-status"
        :style "text-align:center"} ""]
   [:h4 {:style "text-align:center"}
        "Press the up arrow to make your ship go up and 
         avoid the obstacles."]
   [:h4 {:style "text-align:center"}
        "CONTROLS:" [:br {}]
        "Up - Move ship up" [:br {}]
        "space - starts and pauses game \n" [:br {}]]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "/js/cljs.js"}]
   [:script {:src "/js/bootstrap.min.js"}]
   [:script "clj_mini_apps.pong.init();"]])
>>>>>>> e18291381ea2f716e9ce6419bc31fe93e7a72f00