(ns clj-mini-apps.tic-tac-toe
  (:require [hiccup.def :refer [defhtml]]))

(defhtml tic-tac-toe []
  [:head
   [:title "Tic Tac Toe"]]

  [:body
   [:h1 "Tic Tac Toe"]
   [:canvas {:id "canvas"}]])
