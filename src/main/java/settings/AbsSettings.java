package settings;

import java.io.File;

public abstract class AbsSettings {
  protected File propertyFile;
  public AbsSettings(String fileName) {
    String roothPath = System.getProperty("user.dir");
    propertyFile = new File(roothPath + "/src/main/resources/" + fileName);
  }
}
