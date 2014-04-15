(ns datimage.routes.dats
  (:use compojure.core)
  (:require [datimage.util :as util]
            [datimage.models.db :as db]
            [ring.util.response :refer [response]]))

(defn get-dat [id]
  (response (db/get-dat id)))

(defn get-dats []
  (let [dats (db/get-dats)]
    (response
      {:num_results (count dats)
       :dats dats})))

(defn post-dat [params]
  (let [id (val (first (db/create-dat params)))]
    (response (db/get-dat id))))

(defn vote-dat [id]
  (response (db/vote-dat id)))

(defn put-dat [params]
  (response (db/update-dat (:id params) params)))

(defn delete-dat [params]
  (response (db/delete-dat (:id params))))

(defroutes dats-routes
  (context "/api/dat" []
    (GET "/" [] (get-dats))
    (GET "/:id" [id] (get-dat id))
    (POST "/" {params :params} (post-dat params))
    (POST "/vote/:id" [id] (vote-dat id))
    (PUT "/:id" {params :params} (put-dat params))
    (DELETE "/:id" {params :params} (delete-dat params))))
