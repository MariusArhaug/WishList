## Purpose
An app to share and receive wish lists.

## Functionality

- When the app opens, the user has to log in.
  
  ![Log in page](./READMEScreenshots/login.png)
- If the user does not have an account, an account can be created.
  
  ![New user button](./READMEScreenshots/newUserButton.png)
  ![Sign up page](./READMEScreenshots/signup.png)
- A user can make wish lists. When creating a new list, the user needs to choose a name for the wish list.
  
- ![Make new wishlist](./READMEScreenshots/createWishlist.png)
- A user can see the wish lists that they own displayed on the main page.
  
- ![Wish lists](./READMEScreenshots/wishLists.png)
- A user can see the wishes on their wish lists. When clicking on one of the displayed wish lists, the wishes in this specific list will be displayed.
  
  ![A specific wish list](./READMEScreenshots/specificWIshlist.png)
  ![Wishes](./READMEScreenshots/wishes.png)
- A user can add wishes on their wish list. When clicking on one of the displayed wish lists, the user will have the option of adding items to the list.
  
- ![Add new wish](./READMEScreenshots/newWish.png)

## Make wish list (us-1):
As a user, I want to keep track of my wishes for respective events, so that I have it ready when people ask for them.

The user needs to be able to make wish lists. When creating a wish list it is practical to be able to make different wish lists for different events. After creating a wish list, wishes can be added to the list.

## Important points (us-1):
- When making a wish list: Give the list a desired name, and see all the lists the user has already made
- When adding wishes: Have the ability to see all the wishes stored in a wish list, and be able to add new ones

## Log in (us-2):
As a user, I want my lists to be stored somewhere that isn’t local, so that I don’t lose them if I lose my phone.

In order to store lists, the app will be connected to a database. When opening the app, the user will therefore have to log in to their account in order to have access to their wishlists. If the user does not have an account, they will have the option of creating a new account. It is important that the user can see what information is needed to log in to their account. The log in function will protect the user’s information as well.

## Important points (us-2):
- The user must be able to create an account
- The user must be able to log in to an existing account
- The buttons must be visible to the user
- It must be clear which information is needed
