(ns clj-mini-apps.splash)

(def splash
  {:head [:title "Welcome | Clojure Mini Apps"]

   :page
   [:div {:class "page splash-page"}
    [:p {:style "color:black"}
     "Welcome to the home page!"]
    [:div {:id "container"}
     [:p "I should not be here"]]]

   :tail [:script "clj_mini_apps.splash.init();"]})
