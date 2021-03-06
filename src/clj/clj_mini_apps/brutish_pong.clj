(ns clj-mini-apps.brutish-pong)

(def brutish-pong
  {:title "Brutish Pong"

   :page
   [:div {:class "brutish-pong"}
    [:h1 {:style "text-align:center"} "Pong"]
    [:canvas {:id "pong-canvas"
              :style "margin-left:auto;
                      margin-right:auto;
                      display:block"}]
    [:h3 {:id "pong-status"
          :style "text-align:center"} ""]
    [:h4 {:style "text-align:center"}
     "Try to keep the ball from getting past you! You are the margin-left
      paddle."]
    [:h4 {:style "text-align:center"}
     "CONTROLS:" [:br {}]
     "w - Move paddle up" [:br {}]
     "s - Move paddle down" [:br {}]
     "r - restarts game" [:br {}]
     "space - starts and pauses game \n" [:br {}]]]})
