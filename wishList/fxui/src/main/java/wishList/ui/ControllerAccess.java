package wishList.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import wishList.core.User;
import wishList.core.Wish;
import wishList.core.WishList;
import wishList.json.JsonHandler;
import wishList.json.UserDeserializer;
import wishList.utils.Utils;

public class ControllerAccess {
    private final URI endpointURI;
    private User user;
    //private JsonHandler jsonHandler;
    //private UserDeserializer deserializer;
    private ObjectMapper objectMapper;


    //Ferdig
    //Vet ikke når denne skal settes opp da
    public ControllerAccess(URI endpointURI) {
        this.endpointURI = endpointURI;
        //String resourcesPath = Utils.updatePathForAnyOs(new File("").getAbsolutePath(), "src", "main", "resources", "wishList", "restapi");
        //this.jsonHandler = new JsonHandler(resourcesPath);
        //this.deserializer = new UserDeserializer();
        this.objectMapper = new ObjectMapper();
    }

    //Ferdig
    private User getUser(String email, String password){
        if(this.user == null){
            HttpRequest httpRequest = HttpRequest.newBuilder(endpointURI)
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            try {
                //Her bygger vi en ny client tror jeg, og så henter vi responsen (fra serveren jeg har laget?)
                final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
                this.user = objectMapper.readValue(response.body(), User.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this.user;
    }

    //Bruke addUser i JsonHandler? Men det er det jo server som gjør?
    //Ferdig
    private void postUser(User userToSave){
        try {
            String json = objectMapper.writeValueAsString(userToSave);
            HttpRequest request = HttpRequest.newBuilder(wishListURI(userToSave.getEmail()))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            System.out.println(responseString);
            //Okei gjør man noe mer her??
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Ferdig
    private String uriParam(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    //Ferdig
    private URI wishListURI(String name) {
        //Hva skjer her?
        return endpointURI.resolve("wishlist").resolve(uriParam(name));
    }

    //Ferdig
    private void postWishList(WishList wishList){
        try {
            String json = objectMapper.writeValueAsString(wishList);
            HttpRequest request = HttpRequest.newBuilder(wishListURI(wishList.getName()))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            //Okei men hva er poenget med alt det over??

            //vet at vi ikke bruker added, men det kan kanskje fungere her
            //dette er henta fra todolist, mulig vi kan droppe det'
            //++ er ikke det noe som bør gjøres i serveren?
            Boolean added = objectMapper.readValue(responseString, Boolean.class);
            if (added != null) {
                this.user.makeWishList(wishList.getName());
            }
            //this.user.makeWishList(wishList.getName());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    //Ferdig
    private Collection<String> getWishList(WishList wishList){
        Collection<String> wishes = new ArrayList<>();
        wishList.getWishes().forEach(wish -> wishes.add(wish.getName()));
        return wishes;
    }

    //Ferdig
    public void addWishList(WishList wishList){
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

    public Wish getWish(){
        return null;
    }

    //Ferdig
    public void postWish(Wish wish){
        try {
            String json = objectMapper.writeValueAsString(wish);
            HttpRequest request = HttpRequest.newBuilder(wishListURI(wish.getName()))
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


    //Ferdig
    public void deleteWish(Wish wish){
        String wishName = wish.getName();
        WishList wishList = wish.getBelongTo();
        try {
            HttpRequest request = HttpRequest.newBuilder(wishListURI(wishName))
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

    //Ferdig
    public void deleteWishList(WishList wishlist){
        try {
            HttpRequest request = HttpRequest.newBuilder(wishListURI(wishlist.getName()))
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

    //Ferdig
    public void deleteContact(User contact){
        try {
            HttpRequest request = HttpRequest.newBuilder(wishListURI(contact.getEmail()))
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

    //Ferdig
    //vet ikke hvordan man skiller mellom å adde til fil og adde til en User
    public void postContact(User contact){
        try {
            String json = objectMapper.writeValueAsString(contact);
            HttpRequest request = HttpRequest.newBuilder(wishListURI(contact.getEmail()))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            System.out.println(responseString);
            //Okei gjør man noe mer her??
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //hvordan i alle dager skal jeg gjøre dette
    public User getContact(){
        User contact;
        HttpRequest httpRequest = HttpRequest.newBuilder(endpointURI)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            contact = objectMapper.readValue(response.body(), User.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    return contact;
    }

    public WishList getWishList(){
        return null;
    }
}
