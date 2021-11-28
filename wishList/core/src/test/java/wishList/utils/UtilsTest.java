package wishList.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

  @BeforeEach
  public void setup() {}

  @AfterEach
  public void tearDown() {}

  @Test
  public void TestFindFirstOrNull() {
    List<String> strings = new ArrayList<>(Arrays.asList("exists", "here"));
    Predicate<String> exist = e -> e.equals("exists");
    Predicate<String> doesNotExist = e -> e.equals("nothere");
    assertEquals(Utils.findFirstOrNull(strings, exist), "exists");
    assertNull((Utils.findFirstOrNull(strings, doesNotExist)));
  }

  @Test
  public void TestExistInList() {
    List<String> strings = new ArrayList<>(Arrays.asList("exists", "here"));
    Predicate<String> exist = e -> e.equals("exists");
    Predicate<String> doesNotExist = e -> e.equals("nothere");
    assertTrue(Utils.existInList(strings, exist), "exists");
    assertFalse((Utils.existInList(strings, doesNotExist)));
  }

  @Test
  public void TestMap() {
    List<User> users = new ArrayList<>();
    List<String> names = new ArrayList<>(List.of("John", "Jane"));
    User john = new User().setFirstName("John");
    User jane = new User().setFirstName("Jane");

    System.out.println(names);
    users.add(john);
    users.add(jane);

    List<String> names2 = Utils.map(users, User::getFirstName);
    assertTrue(names.containsAll(names2));
  }
}
