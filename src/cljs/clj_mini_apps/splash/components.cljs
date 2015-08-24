(ns clj-mini-apps.splash.components
  (:require [quiescent.core :refer-macros [defcomponent]]
            [quiescent.dom :refer [div input a button section]]
            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]
            [cljs.core.async :as a :refer [<! chan]])
  (:require-macros [cljs.core.async.macros :as am
                    :refer [go]]))

(defcomponent AuthorName
  "Span that attributes an author"
  [{:keys [email name]} channels]
  (a {:className "byline"
      :href (str "mailto:" email)}
     name))

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
       (input {:type "text"
               :name "body"})
       (button {:onClick (fn [evt]
                           (let [input-elems (-> ($ (.-target evt))
                                                 (jq/siblings "input"))
                                 new-article (->> input-elems
                                                  (map $)
                                                  (map (fn [input]
                                                         {(keyword (jq/attr input "name"))
                                                          (jq/val input)}))
                                                  (into {}))]
                             (go (>! (:add-article channels)
                                     new-article))))}
               "Click Me!")))

(defcomponent App
  "Root component"
  [{:keys [articles] :as state} channels]
  (div {}
       (section {:id "main"}
                (ArticleList articles channels)
                (AddArticle state channels))))
