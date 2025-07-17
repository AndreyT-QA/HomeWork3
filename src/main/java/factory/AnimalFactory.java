package factory;

import animals.AbsAnimal;
import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalTypeData;
import data.ColorData;

public class AnimalFactory {

  private long id;
  private String type;
  private String name;
  private int age;
  private int weight;
  private ColorData color;

  public AnimalFactory (long id, String type, String name, int age, int weight, ColorData color) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.age = age;
    this.weight = weight;
    this.color = color;
  }

  public AbsAnimal create(AnimalTypeData animalTypeData) {
    switch (animalTypeData) {
      case CAT: {
        return new Cat(id, type, name, age, weight, color);
      }
      case DOG: {
        return new Dog(id, type, name, age, weight, color);
      }
      case DUCK: {
        return new Duck(id, type, name, age, weight, color);
      }
    }
    throw new RuntimeException(String.format("Animal %s not found", animalTypeData.name().toLowerCase()));
  }
}
