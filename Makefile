.PHONY : start flush

.DEFAULT_GOAL := start

start:
	docker-compose run app mvn test

flush:
	docker-compose down -v