(ns clj-mini-apps.tic-tac-toe
  (:require [monet.canvas :as canvas]
            [jayq.util :refer [log]]))

(def canvas-dom (.getElementById js/document "canvas"))

(def monet-canvas (canvas/init canvas-dom "2d"))

(defn ^:export init []
  (if (and js/document (.-getElementById js/document))
    (do (log "ttt init")
        (canvas/add-entity monet-canvas :background
          (canvas/entity {:x 0 :y 0 :w 600 :h 600}
                  nil
                  (fn [ctx val]
                    (-> ctx
                      (canvas/fill-style "#d1d1d2")
                      (canvas/fill-rect val)
                      (canvas/fill-style "#FFFFFF")
                      (canvas/fill-rect {:x 9 :y 9 :w 582 :h 582})
                      (canvas/fill-style "#000")
                      (canvas/fill-rect {:x 220 :y 79 :w 9 :h 441})
                      (canvas/fill-rect {:x 370 :y 79 :w 9 :h 441})
                      (canvas/fill-rect {:x 79 :y 220 :w 441 :h 9})
                      (canvas/fill-rect {:x 79 :y 370 :w 441 :h 9})
                      )))))))