.PHONY: up down run test verify build image logs

up:        ## Start Postgres, Redis, Kafka, Prometheus, Grafana
	docker compose up -d

down:      ## Stop local dependencies
	docker compose down

run:       ## Run the API locally (dev profile) against docker compose
	./mvnw -pl subly-api -am spring-boot:run

test:      ## Unit tests only
	./mvnw test

verify:    ## Unit + integration tests (needs Docker) + coverage report
	./mvnw verify

build:     ## Package the jar
	./mvnw -DskipTests package

image:     ## Build the container image
	docker build -t ghcr.io/hc-bentill/subly-api:local .

logs:
	docker compose logs -f
