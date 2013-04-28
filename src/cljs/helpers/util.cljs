(ns helpers.util
  (:require [clojure.string :as string]
            [cljs.reader :as reader]
            goog.net.XhrIo)
  (:require-macros [enfocus.macros :as em]))

(defn -escape [text]
  (-> text
      (string/replace "&" "%26")
      (string/replace "+" "%2B")
      (string/replace ":" "%3A")))

(defn -make-post-data [data]
  (.replace
   (reduce str
           (map #(str % "=" (-escape (js/encodeURI (% data)))) (keys data)))
   (js/RegExp. ":" "g") "&"))

(defn ^:export my-post-data
  ([url func] (my-post-data url func {}))
  ([url func data]
     (.send goog.net.XhrIo
            url
            #(func (.getResponseText (.-target %)))
            "POST"
            (-make-post-data data))))

(defn ^:export get-element-value [id]
  (get (em/from js/document
                :value [id] (em/get-prop :value))
       :value))
