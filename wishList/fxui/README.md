# FXUI

WishList has currently a saving function when a user either logs in or registers a new user.

## How to run:

We use `maven` for our project.

The fxui tests will fail unless the server is running.

Start with `mvn clean compile` inside the rest module.

Then run the server:

`mvn spring-boot:run`

Then you need to install and compile in the root directory:

`mvn clean install && mvn compile`

Then to run in the fxui module:

`mvn javafx:run`

> It appears that our maven config is not 100% working so in order to actually run the client you need to first `cd ..` back up to the parent directory then run `mvn -pl fxui javafx:run`

## Folder structure

        rest
        ├── src                                 # Source directory
        |   └─ main
        │      ├── java
        |      |    └──wishList.restapi
        |      |         ├──RESTApplication     # Spring Boot app "mvn spring-boot:run"
        |      |         ├──RESTController      # Controller for incomming HTTP requests
        |      |         ├──WishListService     # Service that interacts with Core
        |      |         └──module-info.java
        |      |
        |      └── resources
        |            └──wishList
        |                 ├──restapi
        |                 └──users
        |                     ├──#email#.json
        ├──rest.iml           ├──#...#
        ├──pom.xml
        └──README.md

## Views and Controllers

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

FXUI has the following classes:

- **WishListApp** : Starts the program; Launch FXML and controllers.

- **AbstractController**: A main controller that has all the cunctions to change betwwen FXML scenes.

- **LoginViewController**: Controller for login scene, that sends user input to **JsonHandler** to checks if the user
  exists or not and then decides to whether let users go to main scene or not.

- **MainViewController** : Currently this controller has no extra functionality other than to serve as a boilerplate to
  let other scenes interact with each other. This will be further improved in next sprint.

- **RegisterViewController** : Lets user fill out register form and checks if this email is unique with other saved
  users.

- **ShowListViewController** : Shows all the wishLists that a user currently owns.

**Resources** In resources you find the different fxml file/ "scenes", named such as _RegisterView.fxml_, etc..

### Test users

We've provided two test users to run this client alongside with the [**server**](../rest/README.md)

- User 1: 'Hallvard Trætteberg' email: haltre@ntnu.no | password: 12345678
- User 2: 'Adrian Stoica' email: adrsto@ntnu.no | password: 12345678
