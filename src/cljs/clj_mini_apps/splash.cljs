(ns clj-mini-apps.splash
  (:require [quiescent.core :refer [render]
             :refer-macros [defcomponent]]
            [quiescent.dom :refer [div input a button section]]
            [cljs.core.async :as a :refer [>! <! chan]]
            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]])
  (:require-macros [cljs.core.async.macros :as am
                    :refer [go]]))

(defcomponent AuthorName
  "Span that attributes an author"
  [author channels]
  (log author)
  (a {:className "byline"
      :href (str "mailto:" (:email author))}
     (:name author)))

(defcomponent Article
  "Component representing an article"
  [article channels]
  (div {:className "article"}
       (div {:className "title"} (:title article))
       (AuthorName (:author article) channels)
       (div {:className "article-body"} (:body article))))

(defcomponent ArticleList
  "Component representing multiple articles"
  [articles channels]
  (div {:className "article-list"}
       (->> (map #(Article % channels) articles)
            (apply div {:className "articles"}))))

(defcomponent AddArticle
  [_ channels]
  (div {:className "add-article"}
       (input {:type "text"
               :name "title"})
       (button {:onClick (fn [evt]
                           (let [inputs (-> ($ (.-target evt))
                                            (jq/siblings "input"))
                                 input-data (map (comp (fn [input]
                                                         {:text (jq/val input)
                                                          :name (jq/attr input "name")})
                                                       $)
                                                 inputs)]
                             (go (>! (:add-article channels)
                                     input-data))))}
               "Click Me!")))

(defcomponent App
  "Root component"
  [state channels]
  (div {}
       (section {:id "main"}
                (ArticleList (:articles state) channels)
                (AddArticle state channels))))

(defn new-state []
  {:articles [{:title "Why core.async is awesome"
               :body "..."
               :author {:name "Luke VanderHart"
                        :email "luke@example.com"}}
              {:title "Programming: you're doing it completely wrong"
               :body "..."
               :author {:name "Rich Hickey"
                        :email "rich@example.com"}}]})

(defn load-app []
  {:state (atom (new-state))
   :channels {:test (chan)
              :add-article (chan)}
   :consumers {:test (fn [state val]
                       (log "consuming " val " with state " state)
                       state)
               :add-article (fn [state inputs]
                              (log "adding article, with inputs: " inputs)
                              state)}})

(let [render-pending? (atom false)]
  (defn my-render [app]
    (when (compare-and-set! render-pending? false true)
      (.requestAnimationFrame
        js/window
        (fn []
          (render (App @(:state app) (:channels app))
                  (.getElementById js/document "container"))
          (reset! render-pending? false))))))

(defn init-updates [app]
  (doseq [[ch update-fn] (:consumers app)]
    (go
      (while true
        (let [val (<! (get (:channels app) ch))
              _ (log "on channel [" ch "], recieved value [" val "]")
              new-state (swap! (:state app) update-fn val)]
          (my-render app))))))

(defn ^:export init []
  (let [app (load-app)]
    (init-updates app)
    (my-render app)))
