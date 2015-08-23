(ns clj-mini-apps.tic-tac-toe)

<<<<<<< HEAD
(def tic-tac-toe
  {:head [:title "Tic Tac Toe | Clojure Mini Apps"]

   :page
   [:div {:class "page ttt-page"}
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
=======
(defhtml tic-tac-toe []
  [:head
   [:title "Tic Tac Toe | Clojure Mini Apps"]
   [:link {:rel "shortcut icon" :href "/images/game controller logo small.png"}]
   [:link {:rel "stylesheet" :href "/css/bootstrap.min.css"}]
   [:link {:rel "stylesheet" :href "/css/splash.css"}]]

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
   [:h1 {:style "text-align:center"} "Tic Tac Toe"]
   [:canvas {:id "ttt-canvas"
             :style "margin-left:auto;
                     margin-right:auto;
                     display:block"}]
   [:h3 {:id "ttt-status"
        :style "text-align:center"} ""]
   [:h4 {:style "text-align:center"}
       "A classic game of Tic Tac Toe! Three in a row wins!" [:br {}]
       "Just left click to place your piece."]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "/js/cljs.js"}]
   [:script {:src "/js/bootstrap.min.js"}]
   [:script "clj_mini_apps.tic_tac_toe.init();"]])
>>>>>>> e18291381ea2f716e9ce6419bc31fe93e7a72f00
