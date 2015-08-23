(defproject clj-mini-apps "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.107"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [compojure "1.4.0"]
                 [jayq "2.5.4"]
                 [hiccup "1.0.5"]
                 [cljs-ajax "0.3.14"]
                 [quil "2.2.6"]
                 [camel-snake-kebab "0.3.2"]
                 [quiescent "0.2.0-alpha1"]
                 [brute "0.2.0"]]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.11"]]
  :source-paths ["src/clj"]
  :cljsbuild {:builds
              [{:source-paths ["src/cljs"],
                :id "main",
                :compiler {:optimizations :whitespace
                           :output-dir    "resources/public/js"
                           :output-to     "resources/public/js/cljs.js"
                           :preamble      ["processing.js"]
                           :externs       ["js/jquery.min.js"]
                           :pretty-print  true
                           :source-map    "resources/public/js/cljs.js.map"}}]}
  :main clj-mini-apps.server
  :ring {:handler clj-mini-apps.server/app
         :auto-refresh? true})
