(ns clj-mini-apps.dunjeon-crawler)

(def dunjeon-crawler
  {:title "Dunjeon Crawler"

   :page
   [:div {:class "page dunjeon-crawler-page"}
    [:h1 {:style "text-align:center"} "Dunjeon Crawler"]
    [:applet {:code "rouje_like.core.desktop_launcher.class"
              :archive "/dunjeon-crawler"
              :width 200
              :height 200}]
    [:h3 {:id "dunjeon-crawler-status"
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
