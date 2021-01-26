(ns datahike.integration-test.config-record-pg-test
  (:require [clojure.test :refer :all]
            [datahike-postgres.core]
            [datahike.integration-test :as it]))

(def config {:store {:backend :pg
                     :host "localhost"
                     :port 5432
                     :user "alice"
                     :password "foo"
                     :dbname "config-test"}})

(defn config-record-pg-test-fixture [f]
  (it/integration-test-fixture config)
  (f))

; We use :each here instead of :once to skip running the test fixtures
; when the :integration test selector is disabled
; https://github.com/technomancy/leiningen/issues/1269
(use-fixtures :each config-record-pg-test-fixture)

(deftest ^:integration config-record-pg-test []
  (it/integration-test config))
