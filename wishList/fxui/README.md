# FXUI

WishList has currently a saving function when a user either logs in or registers a new user.

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

**Resources** In resources you find the different fxml file/ "scenes", named such as *RegisterView.fxml*, etc..

## How to run:

We use `maven` for our project.

First you need to install and compile:

`mvn clean install && mvn compile`

Then to run:

`mvn javafx:run`

### Test users

We've provided two test users to run this client alongside with the [**server**](../rest/README.md)

- User 1: 'Hallvard Trætteberg' email: haltre@ntnu.no | password: 12345678
- User 2: 'Adrian Stoica' email: adrsto@ntnu.no | password: 12345678