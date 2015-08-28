(ns clj-mini-apps.core-test
  (:require [cljs.test :refer-macros [deftest is]
             :as t]))

(deftest sanity-test []
  (is (= 2 2)))
