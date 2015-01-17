(ns clj-mini-apps.tetris
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [jayq.core :as jq :refer [$]]
            [jayq.util :refer [log]]))

(defn ^:export init []
  (when (and js/document (.-getElementById js/document))
    (do (log "tetris init"))))

(def tetris-pieces
  {:i {:configs [#{[1 0] [1 1] [1 2] [1 3]}
                 #{[0 1] [1 1] [2 1] [3 1]}
                 #{[2 0] [2 1] [2 2] [2 3]}
                 #{[0 2] [1 2] [2 2] [3 2]}]
       :color :cyan}
   ;:j {configs  [0x44C0, 0x8E00, 0x6440, 0x0E20] :color :blue}
   ;:l {configs  [0x4460, 0x0E80, 0xC440, 0x2E00] :color :orange}
   :o {:configs (vec (repeat 4 #{[0 0] [0 1] [1 0] [1 1]})) :color :yellow}
   ;:t {configs  [0x06C0, 0x8C40, 0x6C00, 0x4620] :color :purple}
   ;:s {configs  [0x0E40, 0x4C40, 0x4E00, 0x4640] :color :green}
   ;:z {configs  [0x0C60, 0x4C80, 0xC600, 0x2640] :color :red}
   })

(defn lift-fn
  [f]
  (fn [{:keys [type x y dir]}]
    (let [{:keys [configs]} (tetris-pieces type)
          config (map (fn [[i j]]
                          [(+ i x) (+ j y)])
                        (nth configs dir))]
      (doall (map f config)))))

(defn init []
  (do
    (q/rect-mode :corner)
    (q/frame-rate 8)
    (q/smooth)
    (q/background 209 209 210)
    (q/fill 255)
    (q/no-stroke)
    (q/rect 9 9 1082 482)
    (q/fill 0))

  {:is-running? false
   :player {:type :i, :x 15, :y 9, :dir 1}
   :pieces [{:type :i, :x 9, :y 9, :dir 0}]})

(defn draw
  [state]
  (let [w 25 h 25
        draw-rect (fn [[x y]]
                    (q/rect (* w x) (* h y) w h))]
    (do
      (q/rect-mode :corner)
      (q/background 209 209 210)
      (q/fill 255)
      (q/rect 9 9 1082 482)
      (q/fill 0))

    (doall
      (map (lift-fn draw-rect)
           (:pieces state)))

    ((lift-fn draw-rect) (:player state))))

(defn update
  [state]
  (if (:is-running? state)
    (as-> state state
      state)
    state))

(defn handle-input
  [pressed]
  (fn [state]
    (condp = (q/key-as-keyword)
      :w (update-in state [:player :dir] (if pressed #(mod (dec %) 4) identity))
      :s (update-in state [:player :dir] (if pressed #(mod (inc %) 4) identity))
      :a (update-in state [:player :x] (if pressed dec identity))
      :d (update-in state [:player :x] (if pressed inc identity))
      :up    (update-in state [:player :y] (if pressed dec identity))
      :down  (update-in state [:player :y] (if pressed inc identity))
      :left  (update-in state [:player :x] (if pressed dec identity))
      :right (update-in state [:player :x] (if pressed inc identity))
      (keyword " ") (if pressed
                      (-> state
                          (update-in [:is-running?] not))
                      state)
      state)))

(q/defsketch tetris-canvas
  :size [1100 500]
  :setup init
  :key-pressed (handle-input true)
  :draw draw
  :key-released (handle-input false)
  :update update
  :middleware [m/fun-mode])
