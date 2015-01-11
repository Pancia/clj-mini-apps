(ns clj-mini-apps.tic-tac-toe
  (:require [quil.core :as q :include-macros true]

            [jayq.core :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "ttt init"))))

(defn init-board []
  ;Create background and outline
  (q/background 209 209 210)
  (q/fill 255)
  (q/no-stroke)
  (q/rect 9 9 582 582)
  ;Create tic tac toe grid
  (q/fill 0)
  (q/rect 220 79 9 441)
  (q/rect 370 79 9 441)
  (q/rect 79 220 441 9)
  (q/rect 79 370 441 9)
  (q/rect-mode :center))

(def board
  [[nil nil nil]
   [nil nil nil]
   [nil nil nil]])

(def borders
  (map #(assoc % :w 141 :h 141)
    [{:top-left {:x 0 :y 0}}
     {:top-mid {:x 229 :y 0}}
     {:top-right {:x 379 :y 0}}
     {:mid-left {:x 0 :y 229}}
     {:mid-mid {:x 229 :y 229}}
     {:mid-right {:x 379 :y 229}}
     {:btm-left {:x 0 :y 379}}
     {:btm-mid {:x 229 :y 379}}
     {:btm-right {:x 379 :y 379}}]))

(defn add-circle
  []
  (log (q/mouse-x))
  (log (q/mouse-y))
  (q/fill 255 0 0)
  (q/ellipse (q/mouse-x) (q/mouse-y) 100 100)
  (q/fill 255)
  (q/ellipse (q/mouse-x) (q/mouse-y) 90 90))
  ;(update-in state [:rects]
  ;          conj {:x (q/mouse-x) :y (q/mouse-y) :w 10 :h 10}))

(q/defsketch hello
  :host "canvas"
  :size [600 600]
  :setup init-board
  :mouse-pressed add-circle)
