# Release 3

## Architecture

The application now follows a client server archiecture with the server consisting of a REST API created using **Spring Boot** in Java. As such the client [**fxui**](./../../wishList/fxui/README.md) is now designed to be indepenent of the underlyign logic that takes place in the [**core**](../../wishList/core/README.md) package.

<br />
<br />

### Communication between client and server

However to keep this project simple we did not create exclusive **Domain Transfer Objects** (DTOs) for serializing and deserializing the data/objects passed between the client and the server. Meaning that when a user object is passed from the client to the server or vice versa, we're essentially serializign and deserializing the same object only with updated values to reflect changes to the users persisting data.

<br />

### Client

We have chosen to keep JavaFX as the technology for our frontend in order to prioritize other aspects of the app, such as expansion. The client acts as expected and we've completed our uses stories that we wanted to do this iteration.

The client is comprised of a view-controller architecture, where each scene has its own controller to handle various user actions as well as simple logic such as when to update wishlist tables and or give alerts to users. When it comes to sending requests to the server we have a own special **HTTPController** that controlls http requests and what type of data that the server is expecting to be sent.

<br />

### Server

The Server is built using Spring Boot and has various endpoints that support various functions that update the users in storage. You can create users, get users, update wishlists and so on. A REST API is said to be stateless so keeping that in mind it made more sense to keep functions as pure as possible to avoid side effects. The **RESTController** intercepts requests that are sent out to its various endpoints and then calls for the **WishListService** to do something logical to that data it recieved.

As of right now **WishListService** is only a thin layer between the **RESTController** and the underlying saving and loading logic happening in **JsonHandler**. A goal for the next iteration would be to further move logic from **JsonHandler** that dosent directly involve loading or reading to files into the **WishListService**

<br/>
<br />

## Code Quality

To assure good design patterns and bugfree code we still employ CheckStyle and Spotbugs. Combining this with adequate testing (almost at 90% coverage) and good coding rutines, allows us to easily identify nescerray improvements to code before its get merged into **master**

Using **Continous Integration** strategies such as making sure that new code is both able to compile and does not create code breaking alterations was a good way to make the team confident with merging new code into the **master** branch without it causing major code breaks.

For further detail about this pipeline you can view the file `.gitlab-ci.yml`

<br />

## New implemented user stories

### See own wish lists (#Story-3):

As a user, I want to have a way of seeing all the wishlists I have created.

The user should have an easy way of seeing all the wishlists they have created. They should also be able to see the
content of these wishlists. This would be a good place to allow the user to add and remove items from a wishlist.

### Important points (#Story-3):

- A list of the user's wishlists should be ordered in a proper fashion
- How a user can edit the content of a wishlist should be intuitive

### Add friends (#Story-4)

As a user, I want to have a list of my friends who also uses the app. I want to be able to share my wishlists with my
friends, and I want them to be able to share theirs with me.

The user needs to be able to add friends. It should be clear and straight-forward how this is done. The most practical
way of adding friends would be to add them by the email they have registered. If they want to unfriend a friend, they
should be able to do so. The user should also be able to share their wishlists with the friends of their choice.

### Important points (#Story-4)

- The "add friends" button should be visible and easy to see
- It should be intuitive and easy to delete someone as a friend if the user wishes to do so
- The user should be able to choose which friends to share a certain wishlist with
- It should be possible to share a certain wishlist with friends both straight after creating the wishlist, and later

### See wishlists shared with the user (#Story-5)

As a user, I want to have easy way of seeing what wishlists have been shared with me, and see who shared it with me. I
of course also want to see the content of the wishlists shared with me.

The user should be able to get a list of the wishlists shared with them, as well as who the creator/sharer is. The
content of these wishlists should of course also be available to see.

### Important points (#Story-5)

- A list of the wishlists shared with the user should be available and ordered in a proper fashion
- How a user get to see the content of the wishlists should be intuitive

## Sprint 3

In this sprint we have mainly focused on expanding the functionality of the app and creating a rest API. The user can now create wishlists in the app, as well as add wishes to a chosen list. Both wishes and wishlists can be removed. The user can add friends and share wishlists with them. The app now has a client side and a server side. We are still using JSON to save data.

We have expanded our tests to cover the new methods, as well as improved the tests from last iteration. An example of improved tests is that we have rewritten some tests in fxui to test the buttons that change scenes. Seeing as we ran short on time this sprint we came to the conclusion of not doing individual integration tests but instead letting them be handled indirectly by the **FXUI** tests. as of right now in order to make the FXUI tests work, the server has to be up and running before. This way of testing is not best practice and in the next iteration we aim to **mock** our data to create an enviornment for testing the client without depending on our server and another test suit to test the servers endpoint and its own fault checking.

<br />

## Work routines

We have continued to use issues as basis for working on the project. Like in last iteration, we have named our branches after the issues they are connected to, the number of the issue and whether the issue is a CHANGE, DRAFT etc. As an example, the branch used to update our readme files is called 49-change-update-all-readme-files. We have added all the issues from this iteration to the milestone Release3. We start our commit messages with the tags described in [**CONTRIBUTING**](/CONTRIBUTING.md), followed by a short description of what was committed. When merging a branch, we created a merge request and assigned a group member as reviewer. Like in the first iterations, we have written code reviews before merging.

## Diagrams and documentation

[**Documentation of rest service**](./wishList/rest/README.md)
