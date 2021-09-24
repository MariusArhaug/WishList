
# Group gr2121 repository

GitPod Link: [![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2121/gr2121)

## Modules

The project is built with `maven` and to be structured using a modular style. Where you can the parent pom.xml file at the root of this repo.

Following sub-modules are:

- <a href="./core">**core**</a>: Here lies the main *core* logic for the application.
- <a href="./fxui">**fxui**</a>: Here lies javafx-code for GUI as well as controllers for different *views*

Our repo supports the following

- Testing (maven-sunfire-plugin)
- JavaFX running (javafx-maven-plugin)

## README
Explaining groups usage of git:

[CONTRIBUTING.md](CONTRIBUTING.md)

Elaboration of user story, and description og apps purpose and functionality:

[README.md](wishList/core/src/README.md)

Description of main folder:

[README.md](wishList/core/README.md)

Release1:
[README.md](docs/release1/README.md)

## Get started

In order to run this project or in a new environment do the following:

- `mvn clean install`
- `mvn compile`
- `mvn -pl fxui javafx:run` : To run JavaFX.

Tests can be run with

- `mvn -pl core test`

## Directory of the coding project
All the code can be found inside the wishList folder
### Test
wishList/core/src/test/java
- [UserTest.java](./wishList/core/src/test/java/UserTest.java)
- [WishListTest.java](./wishList/core/src/test/java/WishListTest.java)
- [WishTest.java](./wishList/core/src/test/java/WishTest.java)

### Java class logic
wishList/core/src/main/java/core
- [User.java](./wishList/core/src/main/java/core/User.java)
- [WishList.java](./wishList/core/src/main/java/core/WishList.java)
- [Wish.java](./wishList/core/src/main/java/core/Wish.java)

### Controller-App-View

#### App and controller and interface
wishList/fxui/src/main/java
- [AbstractController.java](./wishList/fxui/src/main/java/AbstractController.java)
- [CreateListViewController.java](./wishList/fxui/src/main/java/CreateListViewController.java)
- [LoginViewController.java](./wishList/fxui/src/main/java/LoginViewController.java)
- [MainViewController.java](./wishList/fxui/src/main/java/MainViewController.java)
- [RegisterViewController.java](./wishList/fxui/src/main/java/RegisterViewController.java)
- [ShowListViewController.java](./wishList/fxui/src/main/java/ShowListViewController.java)
- [ViewChanger.java](./wishList/fxui/src/main/java/ViewChanger.java)
- [WishListApp.java](./wishList/fxui/src/main/java/WishListApp.java)

#### FXML
wishList/fxui/src/main/resources
- [CreateListView.fxml](./wishList/fxui/src/main/resources/CreateListView.fxml)
- [LoginView.fxml](./wishList/fxui/src/main/resources/LoginView.fxml)
- [MainView.fxml](./wishList/fxui/src/main/resources/MainView.fxml)
- [RegisterView.fxml](./wishList/fxui/src/main/resources/RegisterView.fxml)
- [ShowListView.fxml](./wishList/fxui/src/main/resources/ShowListView.fxml)

### JSON
wishList/core/src/main/java/json
- [JsonHandler.java](./wishList/core/src/main/java/json/JsonHandler.java)
- [JsonModule.java](./wishList/core/src/main/java/json/JsonModule.java)
- [UserDeserializer.java](./wishList/core/src/main/java/json/UserDeserializer.java)
- [UserSerializer.java](./wishList/core/src/main/java/json/UserSerializer.java)
- [WishDeserializer.java](./wishList/core/src/main/java/json/WishDeserializer.java)
- [WishListDeserializer.java](./wishList/core/src/main/java/json/WishListDeserializer.java)
- [WishSerializer.java](./wishList/core/src/main/java/json/WishSerializer.java)
- [WishListSerializer.java](./wishList/core/src/main/java/json/WishListSerializer.java)

## Dividing of FXML files and the use of multiple controllers

In order to avoid a huge incomprehensible controller we have divided the controller into multiple smaller controllers. One for each FXML file to be exact. All the related JavaFX content for one scene in the app is grouped in its own controller.