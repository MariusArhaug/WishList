# Core

# Main/Core

In the folder **wishList.core** lies the main core logic. With the three classes:

- User-class: Encapsulates user data update state of user. This class has relation to many wishLists


- Wish: Is a single wish encapsulation, that can be further expanded to keep more information about a given wish.


- WishList: keeps track of multiple wishes aswell as its own name and the possibility to expand and encapsulate more
  data.

# Main/JSON

In order to be able to save and load objects to JSON files such as `users.wishList.json` we need to make use of the **
Jackson**
plugin and create our own **Serializers** and **Deserializers**

Where each class has its own **Serializer** and **Deserializer** such as:

- `UserSeralizer / UserDeserializer`

These serializers/deserializers are then being used in the `JsonModule.java` file to tell **Jackson** how to save/load
these objects.

# Main/Utils

When we have simple problems we want to solve like checking if a path has a separator at the end, it is simpler to create pure functions that does not recreate any side-effects and that behave in a deterministic manner. This is what the class `Utils.java` achieves. its more like an module than a class. And ith static methods we achieve this functional behaviour. Some methods are used to create paths from another given patch, and this in turn makes it able to be run wether you are using a Windows OS or Linux OS.  

# Test

Tests have also been splitt into their own packages reflecting how its done in the main folder. The goal is to cover as much code as possible with the tests. 
