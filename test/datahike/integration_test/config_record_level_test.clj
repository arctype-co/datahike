(ns datahike.integration-test.config-record-level-test
  (:require [clojure.test :refer :all]
            [datahike-leveldb.core :as dl]
            [datahike.integration-test :as it]))

(def config {:store {:backend :level :path "/tmp/level-test"}})

(defn config-record-level-fixture [f]
  (it/integration-test-fixture config)
  (f))

; We use :each here instead of :once to skip running the test fixtures
; when the :integration test selector is disabled
; https://github.com/technomancy/leiningen/issues/1269
(use-fixtures :each ^:integration config-record-level-fixture)

(deftest ^:integration config-record-level-test
  (it/integration-test config))
