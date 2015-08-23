(ns clj-mini-apps.lift-off)

(def lift-off
  {:title "Lift-Off"

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
     "space - starts and pauses game \n" [:br {}]]]})
