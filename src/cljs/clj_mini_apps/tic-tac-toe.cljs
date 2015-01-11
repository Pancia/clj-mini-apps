(ns clj-mini-apps.tic-tac-toe
  (:require [monet.canvas :as c]

            [jayq.util :refer [log]]))

(def canvas-dom (.getElementById js/document "canvas"))

(def monet-canvas (c/init canvas-dom "2d"))

(defn ^:export init []
  (if (and js/document (.-getElementById js/document))
    (do (log "ttt init")
        (-> (c/add-entity
              monet-canvas :background
              (c/entity {:x 0 :y 0 :w 600 :h 600}
                        (fn [ctx val]
                          (-> ctx
                              (c/fill-style "#555")
                              (c/fill-rect {:x 250 :y 250 :w 25 :h 25})))
                        (fn [ctx val]
                          (-> ctx
                              (c/fill-style "#555")
                              (c/fill-rect val)
                              (c/fill-style "#FFFFFF")
                              (c/fill-rect {:x 9 :y 9 :w 582 :h 582})
                              (c/fill-style "#000")
                              (c/circle {:x 100 :y 100 :r 50})
                              (c/fill)
                              (c/fill-rect {:x 500 :y 500 :w 10 :h 10})))))
            #_(c/start-updating)))))
