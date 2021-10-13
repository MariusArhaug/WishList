package utils;

import java.io.File;
import java.io.FileWriter;
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
        .reduce(addFileSeparatorAtEnd(path),
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
   * @param path path to be checked
   * @return true/false
   */
  private static boolean hasFileSeparatorAtEnd(String path) {
    return path.substring(path.length() - 1).equals(getFileSeparator());
  }

  /**
   * Add file separator to path if it needs it.
   * @param path path to be updated
   * @return updated path
   */
  private static String addFileSeparatorAtEnd(String path) {
    return hasFileSeparatorAtEnd(path) ? path : path + getFileSeparator();
  }

  /**
   * Reset JSON file.
   * @param path path of file
   * @param filename filename
   * @throws Exception if file not found
   */
  public static void resetFile(String path, String filename) throws Exception {
    String finalPath = hasFileSeparatorAtEnd(path)
                    ? path + filename
                    : path + getFileSeparator() + filename;

    new File(finalPath).delete();
    new File(finalPath).createNewFile();
    FileWriter fileWriter = new FileWriter(finalPath);
    fileWriter.write("[]");
    fileWriter.close();
  }
}
