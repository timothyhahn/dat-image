(ns datimage.models.db
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [datimage.models.schema :as schema]))

(defdb db schema/db-spec)

(defentity dats)

(defn get-dats []
  (select dats))

(defn get-dat [id]
  (first (select dats
                 (where {:id id})
                 (limit 1))))

(defn create-dat [dat]
  (insert dats
          (values dat)))

(defn update-dat [id title image score]
  (update dats
  (set-fields {:title title
               :image image
               :score score})
  (where {:id id})))


(defn delete-dat [id]
  (delete dats
    (where {:id id})))

(defn vote-dat [id]
  (let [score (+ 1 (:score (get-dat id)))]
    (do
      (update dats
      (set-fields {:score score})
      (where {:id id})) (get-dat id))))
