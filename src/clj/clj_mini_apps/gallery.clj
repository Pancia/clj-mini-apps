<<<<<<< HEAD
(ns clj-mini-apps.gallery)

(def gallery
  {:head [:title "Gallery | Clojure Mini Apps"]

   :page
   [:div {:class "page gallery-page"}
    [:p "Welcome to the Gallery!"]]})
=======
(ns clj-mini-apps.gallery
  (:require [hiccup.def :refer [defhtml]]))

(defhtml gallery []
	[:head
		[:meta {:http-equiv "Content-type"
           :content "text/html; charset=utf-8"}]
    	[:title "Gallery | Clojure Mini Apps"]
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
		    [:li {:role "presentation"
				  :class "active"}
		      [:a {:href "/gallery"} "Gallery"]]
		    [:li {:role "presentation"}
		      [:a {:href "/about-us"} "About Us"]]]]
		[:p "Welcome to the Gallery!"]
		[:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   		[:script {:src "/js/bootstrap.min.js"}]
    	[:script {:src "/js/cljs.js"}]])
>>>>>>> e18291381ea2f716e9ce6419bc31fe93e7a72f00