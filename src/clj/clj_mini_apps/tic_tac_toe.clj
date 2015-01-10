(ns clj-mini-apps.tic-tac-toe
  (:require [hiccup.def :refer [defhtml]]))

(defhtml tic-tac-toe []
  [:head
   [:title "Tic Tac Toe"]]

  [:body
   [:p "I'm the body, for ttt"]])
