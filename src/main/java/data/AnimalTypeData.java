package data;

public enum AnimalTypeData {

  CAT,
  DOG,
  DUCK;

  public static AnimalTypeData fromString(String animalType) {
    try {
      return AnimalTypeData.valueOf(animalType.trim().toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}