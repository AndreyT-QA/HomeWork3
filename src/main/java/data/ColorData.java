package data;

public enum ColorData {

  WHITE("Белый"),
  BLACK("Черный"),
  ORANGE("Оранжевый");

  private final String name;

  ColorData (String name) {
    this.name = name;
  }

  public String getName (){
    return name;
  }

  public static ColorData fromString(String animalColor) {
    try {
      return ColorData.valueOf(animalColor.trim().toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
