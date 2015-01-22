(ns clj-mini-apps.games
  (:require [hiccup.def :refer [defhtml]]))

(defhtml games []
	[:head
		[:meta {:http-equiv "Content-type"
           :content "text/html; charset=utf-8"}]
    	[:title "Games"]
    	[:link {:rel "shortcut icon" :href "/images/logo.png"}]
    	[:link {:rel "stylesheet" :href "/css/bootstrap.min.css"}]
   		[:link {:rel "stylesheet" :href "/css/splash.css"}]]
	[:body
		[:div
		  [:ul {:class "nav nav-pills"}
		    [:li {:role "presentation"}
		      [:a {:href "/"} "Home"]]
		    [:li {:role "presentation"
				  :class "active"}
		      [:a {:href ""} "Games"]]
		    [:li {:role "presentation"}
		      [:a {:href ""} "Blog"]]
		    [:li {:role "presentation"}
		      [:a {:href ""} "Gallery"]]
		    [:li {:role "presentation"}
		      [:a {:href ""} "About Us"]]]]
		[:p "Welcome to the Games Page!"]
		[:button {:id "ttt-button"
			      :type "button"
				  :class "btn btn-default btn-lg"}
				  "Play Tic Tac Toe!"]
		[:br {}]
		[:br {}]
	    [:button {:id "pong-button"
			      :type "button"
				  :class "btn btn-default btn-lg"}
				  "Play Pong!"]
		[:br {}]
		[:br {}]
	    [:button {:id "lo-button"
			      :type "button"
				  :class "btn btn-default btn-lg"}
				  "Play Lift-Off!"]
		[:br {}]
		[:br {}]
	    [:button {:id "tetris-button"
			      :type "button"
				  :class "btn btn-default btn-lg"}
				  "Play Tetris!"]
		[:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   		[:script {:src "/js/bootstrap.min.js"}]
    	[:script {:src "/js/cljs.js"}]])
