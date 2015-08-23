(ns clj-mini-apps.tetris)

(def tetris
  {:head [:title "Tetris | Clojure Mini Apps"]

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
     "space - starts and pauses game \n" [:br {}]]]

   :tail [:script "clj_mini_apps.tetris.init();"]})

<<<<<<< HEAD

=======
(defhtml tetris []
  [:head
   [:title "Tetris | Clojure Mini Apps"]
   [:link {:rel "stylesheet" :href "/css/bootstrap.min.css"}]
   [:link {:rel "stylesheet" :href "/css/splash.css"}]
   [:link {:rel "shortcut icon" :href "/images/game controller logo small.png"}]]

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
    "space - starts and pauses game \n" [:br {}]]
   [:script {:src "http://code.jquery.com/jquery-2.1.3.min.js"}]
   [:script {:src "/js/cljs.js"}]
   [:script {:src "/js/bootstrap.min.js"}]
   [:script "clj_mini_apps.tetris.init();"]])
>>>>>>> e18291381ea2f716e9ce6419bc31fe93e7a72f00
