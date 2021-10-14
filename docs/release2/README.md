# Release 2

## Modularisation

The project is divided into modules with dependencies between modules. The build is done with `maven` and every module has their needed configuration.

## Architecture (Full three-layer architecture)

The core logic seen in [**core**](./../../wishList/core/). Here also lies all our serializers and deserializers for our JSON configs [**Jackson**](./../../wishList/core/src/main/java/wishList/json/) while the ui is found in [**fxui**](./../../wishList/fxui/)

## Reflections about documentation and implicit saving.

reflektere over og velge mellom dokumentmetafor (desktop) og implisitt lagring (app)
alle tre lagene spiller sammen

## Code qualities

Tests have been written for the major parts of the code and using **Jacoco** we can display our test coverage.

We also use checkstyle and spotbugs to enforce good code quality. This is also being checked in the gitlab pipline checkout `.gitlab-ci.yml`.

## PlantUML

## Work routines

This iteration went much more smoothly that last iteration, some of it due to less headache with setting up maven and getting it to work across diffrent computers with different OS'. We decided to increase our test coverage to 70-80% which we clearly managed to do. While testing might cause us to end up in `code debt` it is clearly worth it seeing as we then later can easily add new functionality without causing code-breaking changes.

**ADD MORE**
