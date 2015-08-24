(ns clj.clj-mini-apps.webdriver.splash
  (:require [clojure.test :refer :all]
            [ring.adapter.jetty :refer [run-jetty]]
            [clj-webdriver.taxi :refer :all]
            [clj-mini-apps.server :refer [main-routes]]))

(defn start-server []
  (loop [server (run-jetty main-routes {:port 1234, :join? false})]
    (if (.isStarted server)
      server
      (recur server))))

(defn stop-server [server]
  (.stop server))

(defn with-server [t]
  (let [server (start-server)]
    (t)
    (stop-server server)))

(defn start-browser []
  (set-driver! {:browser :firefox}))

(defn stop-browser []
  (quit))

(defn with-browser [t]
  (start-browser)
  (t)
  (stop-browser))

(use-fixtures :once with-server with-browser)

(deftest splash-sanity
  (to (str "http://" "localhost" ":" 1234 "/"))
  (is (= (text "body") "Home\nGames\nBlog\nGallery\nAbout Us\nWelcome to the home page!\nWhy core.async is awesome\nLuke VanderHart\n...\nProgramming: you're doing it completely wrong\nRich Hickey\n...\nClick Me!")))
