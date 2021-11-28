# FXUI

WishList has currently a saving function when a user either logs in or registers a new user.

## How to run:

We use `maven` for our project.

The fxui tests will fail unless the server is running.

### Compile

`mvn clean compile`

Then run the server:

`mvn spring-boot:run`

Then you need to install and compile in the root directory:

`mvn clean install && mvn compile`

Then to run in the fxui module:

`mvn javafx:run`

> It appears that our maven config is not 100% working so in order to actually run the client you need to first `cd ..` back up to the parent directory then run `mvn -pl fxui javafx:run`

## How to ship app

Run in fxui module to make zip file and exe file:

`mvn clean compile javafx:jlink jpackage:jpackage`


## Folder structure

        fxui
        ├── src                                 # Source directory
        |   └─ main
        │      ├── java
        |      |    └──wishList.ui
        |      |         ├──WishListApp         # Main app to run application
        |      |         ├──AbstractController  # Parent controller
        |      |         ├──LoginViewController # Handle log in
        |      |         ├──HTTPController      # Send http requests to server
        |      |         └──module-info.java
        |      |
        |      └── resources
        |            └──wishList
        |                 ├──ui                 # FXML files
        |                 └──users              # Store user data locally
        |                     ├──#email#.json
        ├──pom.xml
        └──README.md

## Views and Controllers

In order to avoid having one controller that maintains multiple function calls to different scenes, we opted instead to
have multiple controllers and views in order to make the code both maintainable if we were to introduce newer
functionality as well as making the code easier to read and digest. This type of architecture is also used in Spring
Boot, so we thought it would be usefull to use it elsewhere aswell.

## Controllers

[**Controllers**](./wishList/fxui/src/main/java/wishList/ui): Controllers for every FXML scene.

To make it very obvious which controller handled which action, we split up the controllers to handle each scene indivuduall. This causes some boilerplate code but common methods shared between the controllers are instead added into the parent controller **AbstractController** this controller in it self is not ment to to directly interact with any FXML scenes, but it gives all its children extra functionality and the ability to easily move user data from one scene to the other.

## FXML

[**FXML**](./wishList/fxui/src/main/resources/wishList/ui): Each FXML file is its own unqiue scene. All created using the **SceneBuilder** tool. We followd our own prototype previously designed and talked about in [**relase2**](../../docs/release2/README.md)

<br />

## FXUI Test

[**Fxui tests**](./wishList/fxui/src/test/java/wishList): FXML tests

To make sure that our scenes act accordingly, there are now added a majority of tests to cover all the nesecarry functionality of each scene. Keep in mind that this is not done useing **Mocking** so in order to get these tests to work, you will have to boot up the server locally. All user changes made during the tests are swiftly removed after the tests are finished running, so no side effects should occur.

> Ideally we would use mocking, but we ran out of time and instead felt this solution solved to of our problems if only temporary. These tests now also behaves as integration test seeing as they are interacting with the server and will fail if the server somehow manages to send the wrong data back during a HTTP request.

<br />

## Test users

We've provided two test users to run this client alongside with the [**server**](../rest/README.md)

- User 1: 'Hallvard Trætteberg' email: haltre@ntnu.no | password: 12345678
- User 2: 'Adrian Stoica' email: adrsto@ntnu.no | password: 12345678
