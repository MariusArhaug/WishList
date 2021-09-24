# Core

# Main/Core
In the folder **wishList.core** lies the main core logic. With the three classes:

- User-class: Encapsulates user data update state of user. This class has relation to many wishLists


- Wish: Is a single wish encapsulation, that can be further expanded to keep more information about a given wish.


- WishList: keeps track of multiple wishes aswell as its own name and the possibility to expand and encapsulate more data.

# Main/JSON

In order to be able to save and load objects to JSON files such as `users.json` we need to make use of the **Jackson** 
plugin and create our own **Serializers** and **Deserializers**

Where each class has its own **Serializer** and **Deserializer** such as:

- `UserSeralizer / UserDeserializer`

These serializers/deserializers are then being used in the `JsonModule.java` file to tell **Jackson** how to save/load these objects.

# Test

Some simple unit tests have been made for our three classes respectively. Each tests is declared with a descriptive method name and only tests a few cases per tests as to make the code easy to read and maintain.
