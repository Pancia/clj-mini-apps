(ns clj-mini-apps.tetris)

(def tetris
  {:title "Tetris"

   :page
   [:div {:class "tetris-page"}
    [:h1 {:style "text-align:center"} "Tetris"]
    [:canvas {:id "tetris-canvas"
              :style "margin-left:auto;
                      margin-right:auto;
                      display:block"}]
    [:h3 {:id "tetris-status"
          :style "text-align:center"} ""]
    [:h4 {:style "text-align:center"}
     "CONTROLS:" [:br {}]
     "wasd for movement" [:br {}]
     "r - restarts game" [:br {}]
     "space - starts and pauses game \n" [:br {}]]]})
