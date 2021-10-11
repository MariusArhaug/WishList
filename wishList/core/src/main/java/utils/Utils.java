package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Utils {

  public static String os = System.getProperty("os.name");

  /**
   * Send in path with additional sub-directories and create path based on os
   * @param path path to update
   * @param newPaths sub directories
   * @return updated path with subdirectories
   */
  public static String updatePathForAnyOS(String path, String ...newPaths) {
    return path
        + Arrays
        .stream(newPaths)
        .reduce("",
            (finalPath, p ) -> finalPath + getFileSeparator() + p)
        + getFileSeparator();
  }

  /**
   * Get correct file separator depending on os
   * @return a string of file separator "/" linux , "\" windows etc
   */
  public static String getFileSeparator() {
    return switch (os) {
      case "Windows" -> "\\";
      default -> "/";
    };
  }

  public static boolean hasFileSeparatorAtEnd(String path) {
    return path.substring(path.length()-1).equals(getFileSeparator());
  }

  public static void resetFile(String path, String filename) throws Exception {
    String finalPath = hasFileSeparatorAtEnd(path) ? path + filename : path + getFileSeparator() + filename;
    new File(finalPath).delete();
    new File(finalPath).createNewFile();
    FileWriter fileWriter = new FileWriter(finalPath);
    fileWriter.write("[]");
    fileWriter.close();
  }
}
