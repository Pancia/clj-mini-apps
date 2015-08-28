(defproject clj-mini-apps "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.107"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [http-kit "2.1.19"]
                 [compojure "1.4.0"]
                 [ring "1.4.0"]
                 [jayq "2.5.4"]
                 [hiccup "1.0.5"]
                 [com.taoensso/sente "1.6.0"] ;core.async => ajax/websocket
                 [quil "2.2.6"]
                 [camel-snake-kebab "0.3.2"]
                 [quiescent "0.2.0-alpha1"]
                 [brute "0.2.0"]]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.11"]]
  :profiles {:dev {:dependencies [[clj-webdriver "0.7.2"
                                   :exclusions [org.seleniumhq.selenium/selenium-server]]
                                  [org.seleniumhq.selenium/selenium-server "2.47.0"]]}}
  :source-paths ["src/clj"]
  :cljsbuild {:builds
              {:main {:source-paths ["src/cljs"],
                      :compiler {:optimizations :whitespace
                                 :output-dir    "resources/public/js"
                                 :output-to     "resources/public/js/cljs.js"
                                 :preamble      ["processing.js"]
                                 :externs       []
                                 :pretty-print  true
                                 :source-map    "resources/public/js/cljs.js.map"}}
               :test {:source-paths ["src/cljs" "test/cljs"]
                      :notify-command ["phantomjs"
                                      "test-resources/test.js"
                                      "test-resources/test.html"]
                      :compiler {:optimizations :whitespace
                                 :pretty-print true
                                 :preamble ["processing.js"]
                                 :output-to "target/cljs/test.js"}}}
              :test-commands {"test" ["phantomjs"
                                      "test-resources/test.js"
                                      "test-resources/test.html"]}}
  :main clj-mini-apps.server
  :ring {:handler clj-mini-apps.server/app
         :auto-refresh? true})
