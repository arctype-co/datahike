(ns sandbox
  (:require [datahike.api :as d]))

(comment

  (def uri "datahike:mem://sandbox")

  (d/delete-database uri)

  (def schema [{:db/ident :name
                :db/cardinality :db.cardinality/one
                :db/index true
                :db/unique :db.unique/identity
                :db/valueType :db.type/string}
               {:db/ident :sibling
                :db/cardinality :db.cardinality/many
                :db/valueType :db.type/ref}
               {:db/ident :age
                :db/cardinality :db.cardinality/one
                :db/valueType :db.type/long}])

  (d/create-database uri :initial-tx schema)

  (def conn (d/connect uri))

  (d/transact! conn [{:name  "Alice", :age   25}
                     {:name  "Bob", :age   35}
                     {:name "Charlie", :age 45 :sibling [[:name "Alice"] [:name "Bob"]]}])

  (d/q '[:find ?e ?n ?a :where [?e :name ?n] [?e :age ?a]] (d/db conn))

  (d/q '[:find ?e ?n ?a :where [?e :name ?n] [?e :age ?a]] (d/history conn)))
