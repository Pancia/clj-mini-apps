(ns clj-mini-apps.test
  (:require [cljs.test :refer-macros [run-all-tests]
             :as t]
            [clj-mini-apps.core-test]))
(enable-console-print!)

(defn ^:export run []
  (run-all-tests #"clj-mini-apps.*-test"))
