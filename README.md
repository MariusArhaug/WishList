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
- Spring Boot (java restapi framework)

# Core

The core module consists of all core logic this application uses. It is comprised of three core classes:

- **User**
- **WishList**
- **Wish**

These classes have their own ways of interacting with each other, and a set of methods and fields.

In here also lies all forms of json serialization and deserilaztion logic and how such core objects should be written to files and saved to acheive persistance.

For further detailed documentation about core structure and logic click [**here**](./wishList/core/)

# FXUI

The FXUI pacakage acts as the client in this application. In here lies all the different scenes the user can interact with and the underlying controllers that handle these user interactions. There is no major logic or computation happeneing in this package aside from it being able to send **HTTP** requests to the server. This is on purpose to make the client as small as possible and not make it depend on underlying logic not direclty used by the user itself.

For further documentation about structure click [**here**](./wishList/fxui/)

# Server

[**Documentation**](./wishList/rest/)

Our server is a RESTful API created using [**Spring Boot**](https://spring.io/projects/spring-boot). For a server to be called a RESTful API it needs to follow a set of rules. Our server organizes entities and methods on unique _URIs_ often referred to as _endpoints_. Clients can get access to these resources using a **HTTP** request that follows a specific format that consists of which endpoint it is requesting, what type of _HTTP_ request being used an its request body (if nessecerray).

> REST standing for Representational State Transfer

> URI standing for Uniformed Resource Identifier

For further documentation about structure click [**here**](./wishList/rest/)
