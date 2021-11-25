# Release 3

## Functionality

We have chosen to keep JavaFX as the technology for our frontend in order to prioritize other aspects of the app, such as expansion. The quality of the code is still checked by CheckStyle and Spotbugs. We have expanded the tests for every module, as described below.


## Sprint 3

In this sprint we have mainly focused on expanding the functionality of the app and creating a rest API. The user can now create wishlists in the app, as well as add wishes to a chosen list. Both wishes and wishlists can be removed. The user can add friends and share wishlists with them. The app now has a client side and a server side. We are still using JSON to save data.

We have expanded our tests to cover the new methods, as well as improved the tests from last iteration. An example of improved tests is that we have rewritten some tests in fxui to test the buttons that change scenes. We have also added a new module, [**integrationtests**](wishList/integrationtests), to test the connection between the app and the server.


## Work routines

We have continued to use issues as basis for working on the project. Like in last iteration, we have named our branches after the issues they are connected to, the number of the issue and whether the issue is a CHANGE, DRAFT etc. As an example, the branch used to update our readme files is called 49-change-update-all-readme-files. We have added all the issues from this iteration to the milestone Release3. We start our commit messages with the tags described in [**CONTRIBUTING**](/CONTRIBUTING.md), followed by a short description of what was committed. When merging a branch, we created a merge request and assigned a group member as reviewer. Like in the first iterations, we have written code reviews before merging.


## Diagrams and documentation
[**Documentation of rest service**](./wishList/rest/README.md)
