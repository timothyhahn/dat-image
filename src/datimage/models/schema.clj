(ns datimage.models.schema
  (:require [clojure.java.jdbc :as sql]
            [noir.io :as io]))

(def db-store "site.db")

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2"
              :subname (str (io/resource-path) db-store)
              :user "sa"
              :password ""
              :make-pool? true
              :naming {:keys clojure.string/lower-case
                       :fields clojure.string/upper-case}})
(defn initialized?
  "checks to see if the database schema is present"
  []
  (.exists (new java.io.File (str (io/resource-path) db-store ".h2.db"))))

(defn create-dats-table
  []
    (sql/with-connection db-spec
      (sql/create-table
        :dats
        [:id "int PRIMARY KEY AUTO_INCREMENT"]
        [:title "varchar(140) NOT NULL"]
        [:image "varchar(140) NOT NULL"]
        [:score "int NOT NULL DEFAULT 0"])))

(defn create-tables
  "creates the database tables used by the application"
  []
  (create-dats-table))
