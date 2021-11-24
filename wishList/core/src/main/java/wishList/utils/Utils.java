package wishList.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Utility methods to be used everywhere.
 */
public class Utils {

  private static final String os = System.getProperty("os.name");

  /**
   * Send in path with additional sub-directories and create path based on os.
   *
   * @param path path to update
   * @param newPaths sub directories
   * @return updated path with subdirectories
   */
  public static String updatePathForAnyOs(String path, String ...newPaths) {
    return
        Arrays
        .stream(newPaths)
        .reduce(removeFileSeparatorAtEnd(path),
            (finalPath, p) -> finalPath + getFileSeparator() + p)
        + getFileSeparator();
  }

  /**
   * Get correct file separator depending on os.
   *
   * @return a string of file separator "/" linux , "\" windows etc
   *
   */
  private static String getFileSeparator() {
    return switch (os) {
      case "Windows" -> "\\";
      default -> "/";
    };
  }

  /**
   * Check if path has file separator at end.
   *
   * @param path path to be checked
   * @return true/false
   */
  static boolean hasFileSeparatorAtEnd(String path) {
    return path.substring(path.length() - 1).equals(getFileSeparator());
  }

  /**
   * Add file separator to path if it needs it.
   *
   * @param path path to be updated
   * @return updated path
   */
  private static String addFileSeparatorAtEnd(String path) {
    return hasFileSeparatorAtEnd(path) ? path : path + getFileSeparator();
  }

  private static String removeFileSeparatorAtEnd(String path) {
    return hasFileSeparatorAtEnd(path) ? path.substring(path.length() - 1) : path;
  }

  /**
   * Reset JSON file.
   *
   * @param path path of file
   * @param filename filename
   * @throws Exception if file not found
   */
  public static void resetFile(String path, String filename) throws Exception {
    String finalPath = addFileSeparatorAtEnd(path) + filename;

    if (new File(finalPath).delete() && new File(finalPath).createNewFile()) {
      try (
              Writer w = new OutputStreamWriter(
                      new FileOutputStream(finalPath),
                      StandardCharsets.UTF_8
              );
              PrintWriter pw = new PrintWriter(w)
      ) {
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

  public static <T> boolean existInList(List<T> list, Predicate<T> predicate) {
    return findFirstOrNull(list, predicate) != null;
  }

  /**
   * Map list to another list.
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
