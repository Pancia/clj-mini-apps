(ns clj-mini-apps.tetris
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))