# Group gr2121 repository

GitPod
Link: [![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121)

Test coverage:
[![coverage report](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121/badges/master/coverage.svg)](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121/-/commits/master)

Pipeline status:
[![pipeline status](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121/badges/master/pipeline.svg)](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121/-/commits/master)

## How to contribute and coding routines:

[CONTRIBUTING.md](./CONTRIBUTING.md)

Elaboration of user story, and description og apps purpose and functionality:

[README.md](wishList/core/src/README.md)

# Documentation agile development iterations:

[1. iteration](docs/release1/README.md)

[2. iteration](docs/release2/README.md)

# Overview of code structure

All the code can be found inside the wishList folder

# Modules

The project is built with `maven` and to be structured using a modular style. Where you can the parent pom.xml file at
the root of this repo.

Following sub-modules are:

- [**core**](wishList/core): Here lies the main _core_ logic for the application.

- [**fxui**](wishList/fxui): Here lies javafx-code for GUI as well as controllers for different _views_

Our repo supports the following

- Testing (maven-sunfire-plugin)
- JavaFX running (javafx-maven-plugin)

# Core

[**Documentation**](./wishList/core/src)

[**Core**](./wishList/core/src/main/java/wishList/core): Core functionality to be used throughout application

[**JSON**](./wishList/core/src/main/java/wishList/json): Serializer/Deserializers for saving objects to JSON files

[**Utils**](./wishList/core/src/main/java/wishList/utils): Utility methods that can be used as functional components

## Core test

[**Core tests**](./wishList/core/src/test/java/wishList): Tests for all sub directories in core

# FXUI

## Dividing of FXML files and the use of multiple controllers

In order to avoid having one controller that maintains multiple function calls to different scenes, we opted instead to
have multiple controllers and views in order to make the code both maintainable if we were to introduce newer
functionality as well as making the code easier to read and digest. This type of architecture is also used in Spring
Boot, so we thought it would be usefull to use it elsewhere aswell.

## Controllers

[**Documentation**](./wishList/fxui/src)

[**Controllers**](./wishList/fxui/src/main/java/wishList/ui): Controllers for every FXML scene

## FXML

[**FXML**](./wishList/fxui/src/main/resources/wishList/ui): Each FXML file is its own unqiue scene

## FXUI Test

[**Fxui tests**](./wishList/fxui/src/test/java/wishList): FXML tests

# REST-API

[**Documentation**](./wishList/rest/)

## Spring Boot

We decided to use [**Spring Boot**](https://spring.io/projects/spring-boot) for our RESTful API seeing as it is the
industry standard and therefore the most searched frameworks on google, allowing us to get as much help as possible.

## HTTP Client

We decided to use [**HTTP
Client**](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/package-summary.html) as a way
of communicating from our FXUI to our REST API. It requires some boilerplate but it is still by far the cleaneast option
out of all the various HTTP packages that Java supports
