package wishList.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Utility methods to be used everywhere. */
public class Utils {

  /**
   * Reset JSON file.
   *
   * @param path path of file
   * @param filename filename
   * @throws Exception if file not found
   */
  public static void resetFile(String path, String filename) throws Exception {
    String finalPath = Paths.get(path, filename).toString();

    if (new File(finalPath).delete() && new File(finalPath).createNewFile()) {
      try (Writer w =
              new OutputStreamWriter(new FileOutputStream(finalPath), StandardCharsets.UTF_8);
           PrintWriter pw = new PrintWriter(w)) {
        pw.print("[]");
      }
    }
  }

  /**
   * Find first element or null.
   *
   * @param list list to be searched
   * @param predicate predicate for search
   * @param <T> Type of list
   * @return element or null
   */
  public static <T> T findFirstOrNull(List<T> list, Predicate<T> predicate) {
    return list.stream().filter(predicate).findFirst().orElse(null);
  }

  /**
   * Check if element exist in list based on predicate.
   *
   * @param list list to check
   * @param predicate predicate to filter list
   * @param <T> Type in List
   * @return true if exists.
   */
  public static <T> boolean existInList(List<T> list, Predicate<T> predicate) {
    return findFirstOrNull(list, predicate) != null;
  }

  /**
   * Maps list to another list.
   *
   * @param list list to be mapped
   * @param fun function to be used in mapping
   * @param <T> Original type
   * @param <V> Mapped type
   * @return Mapped list
   */
  public static <T, V> List<V> map(List<T> list, Function<T, V> fun) {
    return list.stream().map(fun).collect(Collectors.toList());
  }
}
