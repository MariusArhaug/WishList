# REST API

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

## Server

We've created a REST(ful) api for our service **WishList** which allows us in theory to set up the core logic on another
platform independently of the client.

Doing so allows the client to use our services in whatever way the want, whether it to use the application as intended
or just check for user data.

This also allows clients to set up their own platform, if they so choose, that is, using React and so on...

<br />
<br />

## Spring Boot

We decided to use [**Spring Boot**](https://spring.io/projects/spring-boot) for our RESTful API seeing as it is the
industry standard and therefore the most searched frameworks on google, allowing us to get as much help as possible.

<br />
<br />

## HTTP Client

We decided to use [**HTTP
Client**](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/package-summary.html) as a way
of communicating from our FXUI to our REST API. It requires some boilerplate but it is still by far the cleaneast option
out of all the various HTTP packages that Java supports. This package is also directly implemented in the Java SDK, meaning we don't have to really on any other external dependencies.

<br />
<br />

## How to run locally

You can run the server locally using _Maven_

First you need to install and compile (fxui tests will fail unless the server is running):

`mvn clean install && mvn compile`

Then to run (mvn clean compile inside the rest module is often necessary before running the server):

`mvn spring-boot:run`

_Boom_ the server should now run on `http://localhost:8080/wishList/`

<br />
<br />

## Logic

The REST API is intended to be an independent server of the main application. Meaning that this could be run on a server
somewhere else. To achieve this we can communicate with the server using **HTTP** requests. Below are following
endpoints created for our REST-ful API.

When a request is received on the server, the **RESTController** converts the incoming payload and then passes the data
to the **WishListService**.

**WishListService** communicates with the applications core logic and finally returns logical data that the server then
can send back to the client, which in our case is an JavaFX application.

<br />
<br />

## Endpoints

In order to perform actions to our core logic and get data you need to access the following endpoints

> Keep in mind that security has not prioritized thus far and therefore passwords and such are completely visible.

```
    localhost:8080/wishList/api/v1

    Request Type           /ENDPOINT/

    GET                    /wishLists/               # Returns all wishLists
    GET                    /users/                   # Returns all users saved
    GET                    /user/{email}/            # Returns optional based on email
    GET                    /user/{email}/{password}/ # Returns optional based on email and password
    POST                   /user/add/                # Add user from json payload

    POST                   /user/contact/add/        # Add contact to user
    POST                   /user/contact/remove/     # Remove contact from user

    POST                   /user/wish/add/           # Add wish to user's wishlist
    POST                   /user/wish/remove/        # Remove wish from user's wishlist

    POST                   /user/wishList/add/       # Add wishList to user
    POST                   /user/wishList/remove/    # Remove wishList from user
    POST                   /user/wishList/share/     # Share wishList to group


```

## Test users

We've provided two test users to run

- User 1: 'Hallvard Trætteberg' email: haltre@ntnu.no | password: 12345678
- User 2: 'Adrian Stoica' email: adrsto@ntnu.no | password: 12345678
