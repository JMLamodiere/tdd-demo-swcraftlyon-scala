# TDD Demo - Software Crafters Lyon 2021 - Scala

![Build Status](https://github.com/JMLamodiere/tdd-demo-swcraftlyon-scala/actions/workflows/ci.yml/badge.svg?branch=main)

Live coding examples used during [Software Crafters Lyon Meetup may 2021](https://www.meetup.com/fr-FR/Software-Craftsmanship-Lyon/events/277307036/):

- [:fr: :arrow_forward: Voir sur Youtube](https://youtu.be/z0rYlC1ct1I)
- [:uk: :clipboard: Slides](https://speakerdeck.com/jmlamodiere/too-many-mocks-killed-the-test-sw-crafters-lyon-2021)

For a bit of theory, see [:fr: De CRUD à DDD, comment Meetic a sauvé son legacy](https://afup.org/talks/3037-de-crud-a-ddd-comment-meetic-a-sauve-son-legacy)

For PHP examples, see https://github.com/JMLamodiere/tdd-demo-forumphp2020

# Makefile

Run `make` or `make help` to see available commands.

### Test environment

Pre-requisites :

- [docker](https://www.docker.com/)
- [docker-compose](https://docs.docker.com/compose/)

Run tests with:

    make test

## API documentation

- Local : [docs/openapi.yml](docs/openapi.yml)
- Swaggger Hub (with [SwaggerHub API Auto Mocking](https://app.swaggerhub.com/help/integrations/api-auto-mocking)
  activated) : https://app.swaggerhub.com/apis/JMLamodiere/tdd-demo-swcraftlyon-2021/1.0.0

Example :

    curl -i -X POST "https://virtserver.swaggerhub.com/JMLamodiere/tdd-demo-swcraftlyon-2021/1.0.0/runningsessions" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"distance\":5.5,\"shoes\":\"Adadis Turbo2\"}"
