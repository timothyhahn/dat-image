(ns datimage.routes.home
  (:use compojure.core)
  (:require [ring.util.response :refer [resource-response]]))

(defn home-page []
  (resource-response "index.html" {:root "public"}))

(defroutes home-routes
  (GET "/" [] (home-page)))
