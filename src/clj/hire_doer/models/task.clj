(ns hire-doer.models.task
  (:require [hire-doer.db :as db])
  (:use [korma db core]))
        
(defentity example)

(defn select-recent-examples []
  (select example (limit 50) (order :id :DESC)))

(defn create-item [item]
  (insert example (values item)))
