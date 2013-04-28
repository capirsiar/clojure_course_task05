(ns hire-doer.views.main
  (:use compojure.core
        hire-doer.views.helpers)
  (:require [compojure.route :as route]
            [noir.session :as session]
            [noir.response :as resp]
            [hire-doer.models.task :as task]
            ))

(defroutes app-routes
  (GET "/try" [] (resp/redirect "/html/try.html"))
  (POST "/try" r 
        (map #(str % "<br/>") (eval (read-string (str "(list " 
                                                   (clojure.string/replace (:expr (:params r)) #"/" " ") 
                                                   ")")))))
  (POST "/save" r
        (task/create-item
         {:datetime (str (new java.util.Date))
          :expr (:expr (:params r))}))
  
  (POST "/history" [] (emit-str (task/select-recent-examples))))
