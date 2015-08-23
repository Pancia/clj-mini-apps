(ns clj-mini-apps.template
  (:require [hiccup.def :refer [defhtml]]
            [camel-snake-kebab.core :as conv]))

(defhtml template [{:keys [title page]}]
  [:head
   [:title (str title " | Clojure Mini Apps")]
   [:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"}]
   [:link {:rel "stylesheet" :href "/css/splash.css"}]
   [:link {:rel "shortcut icon" :href "/images/game controller logo small.png"}]]

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

   page

   [:div {:class "tail"}
    [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
    [:script {:src "/js/cljs.js"}]
    [:script {:src "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"}]
    [:script (str "clj_mini_apps." (conv/->snake_case_string title) ".init();")]]])
