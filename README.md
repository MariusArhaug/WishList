
# Group gr2121 repository

GitPod Link: [![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121)

## Modules

The project is bulilt with `maven` and to be structured using a modular style. Where you can the parent pom.xml file at the root of this repo. 

Following sub-modules are:

- <a href="./core">**core**</a>: Here lies the main *core* logic for the application.
- <a href="./fxui">**fxui**</a>: Here lies javafx-code for GUI aswell as controllers for different *views*

Our repo supports the following

- Testing (maven-sunfire-plugin)
- JavaFX running (javafx-maven-plugin)


## Get started 

In order to run this project or in a new environment do the following:

- `mvn clean install`
- `mvn compile`
- `mvn -pl fxui javafx:run` : To run JavaFX. 

Tests can be run with 

- `mvn test`
