# set default shell
SHELL := $(shell which bash)

# default shell options
.SHELLFLAGS = -c

.SILENT: ;               # no need for @
.ONESHELL: ;             # recipes execute in same shell
.NOTPARALLEL: ;          # wait for this target to finish
.EXPORT_ALL_VARIABLES: ; # send all vars to shell

.PHONY: help

help: ## Show Help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

# Test / CI environment. Require docker and docker-compose executables

test:test-static ## Run all tests
test:test-dynamic

test-static: ## Run static tests
	docker-compose run --rm sbt sbt scalafmtCheckAll

test-dynamic: ## Run dynamic tests
	docker-compose run --rm sbt sbt test
