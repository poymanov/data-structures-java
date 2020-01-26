.PHONY : start flush

.DEFAULT_GOAL := start

start:
	docker-compose up

flush:
	docker-compose down -v