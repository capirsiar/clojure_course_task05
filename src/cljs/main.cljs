(ns hire-doer.main
  (:require [helpers.util :as util]
            ;[helpers.components :as comps]
            [enfocus.core :as ef]
            [clojure.browser.repl :as repl])
  (:require-macros [enfocus.macros :as em])
  (:use [jayq.core :only [$ css inner]])
  ;(:use-macros [helpers.macros :only [with-user]])
  ) 

;; (repl/connect "http://localhost:9000/repl")

(defn ^:export show-result [id]
  (util/my-post-data "/try"
                     (fn [data] 
                       (em/at js/document
                              [:#result-box] (em/set-attr :style "padding-top: 15px;")
                              [:#result] (em/content data)))
                     {:expr (util/get-element-value :#content)}))

(defn ^:export save-code [id]
  (util/my-post-data "/save"
                     (fn [data]
                       (em/at js/document
                              [:#result] (js/alert "Your code have been saved.")))
                     {:expr (util/get-element-value :#content)}))

(em/defsnippet examples "/html/try.html" [:#examples]
  [{:keys [datetime expr]}]
  [:#examples] (em/set-attr :style "")
  [:h2] (em/content datetime) 
  [:span] (em/content expr))

(defn ^:export show-history [id]
  (util/my-post-data "/history"
                     (fn [data]
                       (em/at js/document
                              [:#repl] (em/set-attr :style "display:none")
                              [:#insert-here] (em/content (map examples (cljs.reader/read-string data)))))))

(defn my-start [] 
  (em/at js/document
    [:#result] (em/content "Hello world!")))  

(set! (.-onload js/window) my-start)

(defn ^:export testing-function [n]
  (even? n))
