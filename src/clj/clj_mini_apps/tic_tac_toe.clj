(ns clj-mini-apps.tic-tac-toe)

(def tic-tac-toe
  {:head [:title "Tic Tac Toe"]

   :page
   [:div {:class "ttt-page"}
    [:h1 {:style "text-align:center"} "Tic Tac Toe"]
    [:canvas {:id "ttt-canvas"
              :style "margin-left:auto;
                      margin-right:auto;
                      display:block"}]
    [:h3 {:id "ttt-status"
          :style "text-align:center"} ""]
    [:h4 {:style "text-align:center"}
     "A classic game of Tic Tac Toe! Three in a row wins!" [:br {}]
     "Just left click to place your piece."]]

   :tail [:script "clj_mini_apps.tic_tac_toe.init();"]})
