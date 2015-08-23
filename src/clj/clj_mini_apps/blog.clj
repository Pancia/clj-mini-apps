<<<<<<< HEAD
(ns clj-mini-apps.blog)

(def blog
  {:head [:title "Blog | Clojure Mini Apps"]}

  :page [:p "Welcome to the Blog!"])
=======
(ns clj-mini-apps.blog
  (:require [hiccup.def :refer [defhtml]]))

(defhtml blog []
	[:head
		[:meta {:http-equiv "Content-type"
           :content "text/html; charset=utf-8"}]
    	[:title "Blog | Clojure Mini Apps"]
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
		    [:li {:role "presentation"
				  :class "active"}
		      [:a {:href "/blog"} "Blog"]]
		    [:li {:role "presentation"}
		      [:a {:href "/gallery"} "Gallery"]]
		    [:li {:role "presentation"}
		      [:a {:href "/about-us"} "About Us"]]]]
		[:p "Welcome to the Blog!"]
		[:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   		[:script {:src "/js/bootstrap.min.js"}]
    	[:script {:src "/js/cljs.js"}]])
>>>>>>> e18291381ea2f716e9ce6419bc31fe93e7a72f00
