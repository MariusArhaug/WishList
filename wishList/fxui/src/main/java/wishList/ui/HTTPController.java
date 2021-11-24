package wishList.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import wishList.core.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

class HTTPController {
  private static final String ENDPOINT_URI = "http://localhost:8080/wishList";
  private final ObjectMapper objectMapper = new ObjectMapper();

  private final HttpClient client = HttpClient.newHttpClient();

  private User user;

  private URI getEndpointUri(String endpoint) {
    return URI.create(ENDPOINT_URI + endpoint);
  }

  /**
   * Get user from REST API.
   *
   * @param email email of user
   * @param password password of user
   * @return Optional user
   */
  Optional<User> getUser(String email, String password) throws IOException, InterruptedException {
    if (this.user != null) {
      return Optional.of(this.user);
    }
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(getEndpointUri("/user/" + email + "/" + password))
            .header("Accept", "application/json")
            .GET()
            .build();

    System.out.println("!!!!");
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());
    Optional<User> user = Optional.of(objectMapper.readValue(response.body(), User.class));

    this.user = user.get();
    return user;
  }

  // Bruke addUser i JsonHandler? Men det er det jo server som gjør?
  // Ferdig
  /*
  private void postUser(User userToSave) {
    try {
      String json = objectMapper.writeValueAsString(userToSave);
      HttpRequest request =
          HttpRequest.newBuilder(wishListURI(userToSave.getEmail()))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      // Okei gjør man noe mer her??
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }



  // Ferdig
  private void postWishList(WishList wishList) {
    try {
      String json = objectMapper.writeValueAsString(wishList);
      HttpRequest request =
          HttpRequest.newBuilder(wishListURI(wishList.getName()))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      // Okei men hva er poenget med alt det over??

      // vet at vi ikke bruker added, men det kan kanskje fungere her
      // dette er henta fra todolist, mulig vi kan droppe det'
      // ++ er ikke det noe som bør gjøres i serveren?
      Boolean added = objectMapper.readValue(responseString, Boolean.class);
      if (added != null) {
        this.user.makeWishList(wishList.getName());
      }
      // this.user.makeWishList(wishList.getName());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // Ferdig
  private Collection<String> getWishList(WishList wishList) {
    Collection<String> wishes = new ArrayList<>();
    wishList.getWishes().forEach(wish -> wishes.add(wish.getName()));
    return wishes;
  }

  // Ferdig
  public void addWishList(WishList wishList) {
    postWishList(wishList);
  }

  /*
  //MÅ ENDRES
  public Collection<String> getAllWishLists(){
      Collection<String> lists = new ArrayList<>();
      //Skal endres her
      getUser().iterator().forEach(wishList -> lists.add(wishList.getName()));
      return lists;
  } */

  /*
  public Wish getWish() {
    return null;
  }

  // Ferdig
  public void postWish(Wish wish) {
    try {
      String json = objectMapper.writeValueAsString(wish);
      HttpRequest request =
          HttpRequest.newBuilder(wishListURI(wish.getName()))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean added = objectMapper.readValue(responseString, Boolean.class);
      if (added != null) {
        user.addWish(wish.getBelongTo(), wish);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // Ferdig
  public void deleteWish(Wish wish) {
    String wishName = wish.getName();
    WishList wishList = wish.getBelongTo();
    try {
      HttpRequest request =
          HttpRequest.newBuilder(wishListURI(wishName))
              .header("Accept", "application/json")
              .DELETE()
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean removed = objectMapper.readValue(responseString, Boolean.class);
      if (removed != null) {
        user.removeWish(wishList.getName(), wishName);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // Ferdig
  public void deleteWishList(WishList wishlist) {
    try {
      HttpRequest request =
          HttpRequest.newBuilder(wishListURI(wishlist.getName()))
              .header("Accept", "application/json")
              .DELETE()
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean removed = objectMapper.readValue(responseString, Boolean.class);
      if (removed != null) {
        user.removeWishList(wishlist.getName());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // Ferdig
  public void deleteContact(User contact) {
    try {
      HttpRequest request =
          HttpRequest.newBuilder(wishListURI(contact.getEmail()))
              .header("Accept", "application/json")
              .DELETE()
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean removed = objectMapper.readValue(responseString, Boolean.class);
      if (removed != null) {
        user.removeContact(contact);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // Ferdig
  // vet ikke hvordan man skiller mellom å adde til fil og adde til en User
  public void postContact(User contact) {
    try {
      String json = objectMapper.writeValueAsString(contact);
      HttpRequest request =
          HttpRequest.newBuilder(wishListURI(contact.getEmail()))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(json))
              .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      // Okei gjør man noe mer her??
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // hvordan i alle dager skal jeg gjøre dette
  public User getContact() {
    User contact;
    HttpRequest httpRequest =
        HttpRequest.newBuilder(endpointURI).header("Accept", "application/json").GET().build();
    try {
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
      contact = objectMapper.readValue(response.body(), User.class);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return contact;
  }

  public WishList getWishList() {
    return null;
  }
   */
}
