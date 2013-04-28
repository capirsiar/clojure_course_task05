(ns hire-doer.handler
  (:use compojure.core
        [korma db core])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [noir.util.middleware :as noir]
            [noir.session :as session]
            [hire-doer.db :as db]
            [noir.response :as resp]
            [hire-doer.views.main :as v-main]))

(defdb korma-db db/default-conn)

(defroutes app-routes
  (GET "/" [] (resp/redirect "/html/try.html"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> [v-main/app-routes
       (handler/site app-routes)]
      noir/app-handler
      noir/war-handler))

