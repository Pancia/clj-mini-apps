(ns clj-mini-apps.splash
  (:require [hiccup.def :refer [defhtml]]))

(def splash
  {:head [:title "Welcome | Clojure Mini Apps"]

   :page
   [:div {:class "page splash-page"}
    [:p {:style "color:black"}
     "Welcome to the home page!"]]

   :tail [:script "clj_mini_apps.splash.init();"]})
