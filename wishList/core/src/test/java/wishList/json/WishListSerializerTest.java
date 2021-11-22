package wishList.json;

class WishListSerializerTest {
  /*private final File wishListsFile = new File(JsonHandlerTest.testFolder + "wishLists.json");
  =======
    private final File wishListsFile = new File(JsonHandlerTest.testFolder, "wishLists.json");
  >>>>>>> 094e65c... CHANGE: refactor util file methods
    private WishList wishList;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() throws IOException {
      wishList = new WishList("Birthday");
      mapper = new ObjectMapper();
      mapper.getFactory().createGenerator(wishListsFile, JsonEncoding.UTF8);
    }

    @AfterEach
    void tearDown() {
      wishList = null;
      mapper = null;
    }

    @Test
    void wishListSerializerTest() throws Exception {

      List<WishList> wishLists = new ArrayList<>(List.of(wishList));
      mapper.writeValue(wishListsFile, wishLists);

      List<WishList> wishListsFromFile =
          mapper.readValue(wishListsFile, new TypeReference<List<WishList>>() {});
      System.out.println(wishListsFromFile);

      WishList wishList = wishListsFromFile.get(0);
      assertEquals(wishList.getName(), "Birthday");

      List<Wish> wishes = new ArrayList<>();
      assertEquals(wishListsFromFile.get(0).getWishes(), wishes);
      assertNull(wishList.getOwner());
    }*/
}
