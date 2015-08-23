(ns clj-mini-apps.games)

(def games
  {:head [:title "Games | Clojure Mini Apps"]

   :page
   [:div {:class "page games-page"}
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
     "Play Tetris"]]})
