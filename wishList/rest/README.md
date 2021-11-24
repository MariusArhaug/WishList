# REST API

We've created a REST(ful) api for our service WishList which allows us in theory to set up the core logic on another
platfrom independently of the client.

Doing so allows the client to use our services in whatever way the want, wether it to use the application as intended or
just check for user data.

This also allows clients to set up their own platform, if they so choose, i.e using React and so on..

## How to run locally

You can run the server locally using *Maven*

First you need to install and compile:

`mvn clean install && mvn compile`

Then to run:

`mvn spring-boot:run`

*Boom* the server should now run on `http://localhost:8080/wishList/`

## Endpoints

In order to perform actions to our core logic and get data you need to access the following endpoints

> Keep in mind that security has not prioritized thus far and therefore passwords and such are completely visible.

```
    localhost:8080/wishList/
    
    GET                    /users/                   # Returns all users saved
    GET                    /user/{email}/{password}/ # Returns optional user if he/she exists with email and password
    .
    .
    .
    
```