## Purpose

An app to share and receive wish lists.

## Log in (#Story-1):

As a user, I want my lists to be stored somewhere that isn’t local, so that I don’t lose them if I lose my phone.

In order to store lists, the app will save user data such as name, email and password (not yet hashed). This data will
then be stored in a JSON file called `users.wishList.json` . When opening the app, the user will therefore have to log
in to their account in order to have access to their wishlists. If the user does not have an account, they will have the
option of creating a new account. It is important that the user can see what information is needed to log in to their
account. The log in function will protect the user’s information as well.

## Important points (#Story-1):

- The user must be able to create an account
- The user must be able to log in to an existing account
- The buttons must be visible to the user
- It must be clear which information is needed
- The user data must be saved somehow

## Make wish list (#Story-2):

As a user, I want to keep track of my wishes for respective events, so that I have it ready when people ask for them.

The user needs to be able to make wish lists. When creating a wish list it is practical to be able to make different
wish lists for different events. After creating a wish list, wishes can be added to the list.

## Important points (#Story-2):

- When making a wish list: Give the list a desired name, and see all the lists the user has already made
- When adding wishes: Have the ability to see all the wishes stored in a wish list, and be able to add new ones

## See own wish lists (#Story-3):

As a user, I want to have a way of seeing all the wishlists I have created.

The user should have an easy way of seeing all the wishlists they have created. They should also be able to see the
content of these wishlists. This would be a good place to allow the user to add and remove items from a wishlist.

## Important points (#Story-3):

- A list of the user's wishlists should be ordered in a proper fashion
- How a user can edit the content of a wishlist should be intuitive

## Add friends (#Story-4)

As a user, I want to have a list of my friends who also uses the app. I want to be able to share my wishlists with my
friends, and I want them to be able to share theirs with me.

The user needs to be able to add friends. It should be clear and straight-forward how this is done. The most practical
way of adding friends would be to add them by the email they have registered. If they want to unfriend a friend, they
should be able to do so. The user should also be able to share their wishlists with the friends of their choice.

## Important points (#Story-4)

- The "add friends" button should be visible and easy to see
- It should be intuitive and easy to delete someone as a friend if the user wishes to do so
- The user should be able to choose which friends to share a certain wishlist with
- It should be possible to share a certain wishlist with friends both straight after creating the wishlist, and later

## See wishlists shared with the user (#Story-5)

As a user, I want to have easy way of seeing what wishlists have been shared with me, and see who shared it with me. I
of course also want to see the content of the wishlists shared with me.

The user should be able to get a list of the wishlists shared with them, as well as who the creator/sharer is. The
content of these wishlists should of course also be available to see.

## Important points (#Story-5)

- A list of the wishlists shared with the user should be available and ordered in a proper fashion
- How a user get to see the content of the wishlists should be intuitive

## Sprint 1:

During the planning of this first iteration our main goal was to implement some core functionalty such as user login and
user create list. Our processes followed this strategy:

- We created a milestone for each user story and then created subsequent issues.
- Issues was created as a way to divide a user story into its smallest parts. Issues were then assigned to individual
  members and reviewers.
- Create MR's for Issues for that one other member of the group has to review along side with GitLab CI pipeline tests.

We originally thought we had time to complete both issues, but after many hours of configuring maven and pom files we
had dug our selves into code debt.

Knowing now that our modules structure is finally working, the following Sprints can be done in a way more efficient way
and hopefully with more reviewers for merge requests.

We've completed User Story 1, with complete support for UI and Storage of users, and we've only implemented the core
functionality of use case Story-2.

# Functionality

- When the app opens, the user has to log in.

  ![Log in page](../../../docs/release1/resoursces/login.png)

- If the user does not have an account, an account can be created.

  ![New user button](../../../docs/release1/resoursces/newUserButton.png)
  ![Sign up page](../../../docs/release1/resoursces/signup.png)

- A user can make wish lists. When creating a new list, the user needs to choose a name for the wish list.
- ![Make new wishlist](../../../docs/release1/resoursces/createWishlist.png)
- A user can see the wish lists that they own displayed on the main page.
- ![Wish lists](../../../docs/release1/resoursces/wishLists.png)
- A user can see the wishes on their wish lists. When clicking on one of the displayed wish lists, the wishes in this
  specific list will be displayed.

  ![A specific wish list](../../../docs/release1/resoursces/specificWIshlist.png)
  ![Wishes](../../../docs/release1/resoursces/wishes.png)

- A user can add wishes on their wish list. When clicking on one of the displayed wish lists, the user will have the
  option of adding items to the list.
- ![Add new wish](../../../docs/release1/resoursces/newWish.png)
