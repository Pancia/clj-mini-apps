(ns clj-mini-apps.splash.core
  (:require [clj-mini-apps.splash.data :as data]
            [clj-mini-apps.splash.components :refer [App]]
            [quiescent.core :refer [render]]
            [jayq.util :refer [log]]
            [cljs.core.async :as a :refer [<! chan]])
  (:require-macros [cljs.core.async.macros :as am
                    :refer [go]]))

(let [render-pending? (atom false)]
  (defn my-render [{:keys [state channels]}]
    (when (compare-and-set! render-pending? false true)
      (.requestAnimationFrame
        js/window
        (fn []
          (render (App @state channels)
                  (.getElementById js/document "container"))
          (reset! render-pending? false))))))

(defn init-updates [{:keys [consumers channels state] :as app}]
  (doseq [[ch update-fn] consumers]
    (go
      (while true
        (let [val (<! (get channels ch))
              _ (log "on channel [" ch "], recieved value [" val "]")
              new-state (swap! state update-fn val)]
          (my-render app))))))

(defn load-app []
  {:state (atom (data/init))
   :channels {:test (chan)
              :add-article (chan)}
   :consumers {:test data/test
               :add-article data/add-article}})

(defn ^:export init []
  (let [app (load-app)]
    (init-updates app)
    (my-render app)))
