# Group gr2121 repository

GitPod
Link: [![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121)

Test coverage:
[![coverage report](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121/badges/master/coverage.svg)](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121/-/commits/master)

Pipeline status:
[![pipeline status](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121/badges/master/pipeline.svg)](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121/-/commits/master)

# Documentation agile development iterations:

- [1. iteration](docs/release1/README.md)

- [2. iteration](docs/release2/README.md)

- [3. iteration](docs/release3/README.md)

## How to contribute and how to run application.

- [**CONTRIBUTING.md**](./CONTRIBUTING.md)

# Overview of code structure

All the code can be found inside the wishList folder

# Modules

The project is built with `maven` and to be structured using a modular style. Where you can the parent pom.xml file at
the root of this repo.

Following sub-modules are:

- [**core**](wishList/core): Here lies the main _core_ logic for the application.

- [**fxui**](wishList/fxui): Here lies javafx-code for GUI as well as controllers for different _views_

- [**rest**](wishList/rest): Here lies the code for the rest server

Our repo supports the following

- Testing (maven-sunfire-plugin)
- JavaFX running (javafx-maven-plugin)

# Core

The core module consists of all core logic this application uses. It is comprised of three core classes, **User**, **WishList** and **Wish** which has their own ways of interacting with each other set of methods and fields.

In here also lies all forms of json serialization and deserilaztion logic and how such core objects should be written to files and saved to acheive persistance.

For further detailed documentation about core structure and logic click [**here**](./wishList/core/)

# FXUI

The FXUI pacakage acts as the client in this application. In here lies all the different scenes the user can interact with and the underlying controllers that handle these user interactions. There is no major logic or computation happeneing in this package aside from it being able to send **HTTP** requests to the server. This is on purpose to make the client as small as possible and not make it depend on underlying logic not direclty used by the user itself.

For further documentation about structure click [**here**](./wishList/fxui/)

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
