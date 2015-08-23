(ns clj-mini-apps.splash)

(def splash
  {:title "Splash"

   :page
   [:div {:class "page splash-page"}
    [:p {:style "color:black"}
     "Welcome to the home page!"]
    [:div {:id "container"}
     [:p "I should not be here"]]]})
