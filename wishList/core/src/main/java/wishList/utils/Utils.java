package wishList.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/** Utility methods to be used everywhere. */
public class Utils {

  /**
   * Check if path has file separator at end.
   *
   * @param path path to be checked
   * @return true/false
   */
  static boolean hasFileSeparatorAtEnd(String path) {
    return path.substring(path.length() - 1).equals(File.separator);
  }

  /**
   * Add file separator to path if it needs it.
   *
   * @param path path to be updated
   * @return updated path
   */
  private static String addFileSeparatorAtEnd(String path) {
    return hasFileSeparatorAtEnd(path) ? path : path + File.separator;
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
      try (Writer w =
              new OutputStreamWriter(new FileOutputStream(finalPath), StandardCharsets.UTF_8);
          PrintWriter pw = new PrintWriter(w)) {
        pw.print("[]");
      }
    }
  }
}
