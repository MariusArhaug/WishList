# Core

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

## Core/Core

[**Core**](./src/main/java/wishList/core): Core functionality to be used throughout application

In the folder **wishList.core** lies the main core logic. With the three classes:

- User-class: Encapsulates user data update state of user. This class has relation to many wishLists

- Wish: Is a single wish encapsulation, that can be further expanded to keep more information about a given wish.

- WishList: keeps track of multiple wishes aswell as its own name and the possibility to expand and encapsulate more
  data.

## Core/JSON

[**JSON**](./src/main/java/wishList/json): Serializer/Deserializers for saving objects to JSON files

In order to be able to save and load objects to JSON files such as `users.wishList.json` we need to make use of the **
Jackson**
plugin and create our own **Serializers** and **Deserializers**

Where each class has its own **Serializer** and **Deserializer** such as:

- `UserSeralizer / UserDeserializer`

These serializers/deserializers are then being used in the `JsonModule.java` file to tell **Jackson** how to save/load
these objects.

## Core/Utils

[**Utils**](./src/main/java/wishList/utils): Utility methods that can be used as functional components

The `Utils.java` provides various static functions that can be used to remove code duplication and make code more readable. It supports various `generic` functions such as mapping and checking if elements exists or if its null. These fuctions have made other files much simpler in logic and also made them much more readable.

# Test

[**Core tests**](./src/test/java/wishList): Tests for all sub directories in core

Tests have also been splitt into their own packages reflecting how its done in the main folder. The goal is to cover as much code as possible with the tests.
