# FXUI 

WishList has currently a saving function when a user either logs in or registers a new user. This data will then be saved in a `users.json` file. 

FXUI has the following classes:

- **wishList.ui.WishListApp** : Starts the program; Launch FXML and controllers.

- **wishList.ui.AbstractController**: A main controller tha implements changeView methods from the interface **wishList.ui.ViewChanger**

- **wishList.ui.LoginViewController**: Controller for login scene, that sends user input to **JsonHandler** to checks if the user exists or not and then decides to whether let users go to main scene or not.

- **wishList.ui.MainViewController** : Currently this controller has no extra functionality other than to serve as a boilerplate to let other scenes interact with each other. This will be further improved in next sprint. 

- **wishList.ui.RegisterViewController** : Lets user fill out register form and checks if this email is unique with other saved users. 

- **wishList.ui.ShowListViewController** : Shows all the wishLists that a user currently owns. 

**Resources** In resources you find both the `users.json` file aswell as the different fxml file/ "scenes", named such as *RegisterView.fxml*, etc..


