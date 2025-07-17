package animals.birds;

import animals.AbsAnimal;
import data.ColorData;

public class Duck extends AbsAnimal implements IFlying {
  public Duck(long id, String type, String name, int age, int weight, ColorData color) {
    super(id, type, name, age, weight, color);
  }

  @Override
  public void say () {
    System.out.println("Кря");
  }
  @Override
  public void fly () {
    System.out.println("Я лечу");
  }

}
