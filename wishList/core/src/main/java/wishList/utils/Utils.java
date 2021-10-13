package wishList.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
  public static String getFileSeparator() {
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
  public static boolean hasFileSeparatorAtEnd(String path) {
    return path.substring(path.length() - 1).equals(getFileSeparator());
  }

  /**
   * Add file separator to path if it needs it.
   *
   * @param path path to be updated
   * @return updated path
   */
  public static String addFileSeparatorAtEnd(String path) {
    return hasFileSeparatorAtEnd(path) ? path : path + getFileSeparator();
  }

  public static String removeFileSeparatorAtEnd(String path) {
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
        pw.println("[]");
      }
    }
  }
}
