package animals.pets;

import animals.AbsAnimal;
import data.ColorData;

public class Cat extends AbsAnimal {

  public Cat(long id, String type, String name, int age, int weight, ColorData color) {
    super(id, type, name, age, weight, color);
  }

  @Override
  public void say () {
    System.out.println("Мяу");
  }


}

