import animals.AbsAnimal;
import dao.AnimalTable;
import connector.MySqlDbConnector;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandData;
import factory.AnimalFactory;
import org.w3c.dom.ls.LSOutput;
import tools.NumberValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    List<AbsAnimal> animals = new ArrayList<>();
    Scanner console = new Scanner(System.in);
    NumberValidator numberValidator = new NumberValidator();
    AnimalTable animalTable = new AnimalTable();


    while (true) {
      System.out.println("Введи команду add/list/update/filter/exit:");
      String input = console.next().trim().toLowerCase();
      CommandData command = CommandData.fromString(input);
      AnimalTypeData animalTypeData;
      int animalAge;
      int animalWeight;
      ColorData colorData;


      if (!input.equals("add") && !input.equals("list") && !input.equals("update") && !input.equals("filter")
          && !input.equals("exit")) {
        System.out.println("Вы ввели неверную команду, повторите снова");
        continue;
      }
      switch (command) {
        case ADD: {
//          AnimalTypeData animalTypeData;
          while (true) {
            System.out.println("Выберите животное для добавления: cat/dog/duck");
            String animalTypeUser = console.next().trim().toLowerCase();
            AnimalTypeData animalType = AnimalTypeData.fromString(animalTypeUser);

            if (!animalTypeUser.equals("cat") && !animalTypeUser.equals("dog") && !animalTypeUser.equals("duck")) {
              System.out.println("Животное не найдено, повторите снова");
              continue;
            }
            animalTypeData = AnimalTypeData.valueOf(animalTypeUser.toUpperCase());
            break;
          }

          System.out.println("Введите имя животного");
          String animalNameUser = console.next().trim();


//          int animalAge;
          while (true) {
            System.out.println("Введите возраст животного");
            String animalAgeUser = console.next().trim();

            if (!numberValidator.isNumber(animalAgeUser)) {
              System.out.println("Введен некорректный возраст животного. Повторите снова");
              continue;
            }
            animalAge = Integer.parseInt(animalAgeUser);
            break;
          }

//          int animalWeight;
          while (true) {
            System.out.println("Введите вес животного");
            String animalWeightUser = console.next().trim();

            if (!numberValidator.isNumber(animalWeightUser)) {
              System.out.println("Введен некорректный вес животного. Повторите снова");
              continue;
            }
            animalWeight = Integer.parseInt(animalWeightUser);
            break;
          }

//          ColorData colorData;
          while (true) {
            System.out.println("Выберите цвет животного: white/black/orange");
            String animalColorUser = console.next().trim().toLowerCase();
            ColorData animalColor = ColorData.fromString(animalColorUser);

            if (!animalColorUser.equals("white") && !animalColorUser.equals("black") && !animalColorUser.equals("orange")) {
              System.out.println("Цвет не найден, повторите снова");
              continue;
            }
            colorData = ColorData.valueOf(animalColorUser.toUpperCase());
            break;
          }

          AbsAnimal animal = new AnimalFactory(0, animalTypeData.name(), animalNameUser, animalAge, animalWeight, colorData).create(animalTypeData);
          animalTable.insertAnimal(animal);
          break;
        }
        case LIST: {
          for (AbsAnimal animal : animalTable.findAll()) {
            System.out.println(animal.toString());
          }
          break;
        }
        case UPDATE: {
           while (true) {
            System.out.println("Введите номер животного для редактирования (целое, положительное число)");
            long searchID = console.nextLong();

            AbsAnimal animalToUpdate = animalTable.findById(searchID);
            if (animalToUpdate != null) {
              System.out.println(animalToUpdate.toString());
              while (true) {
                System.out.println("Выберите тип животного: cat/dog/duck");
                String animalTypeUser = console.next().trim().toLowerCase();
                AnimalTypeData animalType = AnimalTypeData.fromString(animalTypeUser);

                if (!animalTypeUser.equals("cat") && !animalTypeUser.equals("dog") && !animalTypeUser.equals("duck")) {
                  System.out.println("Животное не найдено, повторите снова");
                  continue;
                }
                animalToUpdate.setType(animalTypeUser);
                animalTypeData = AnimalTypeData.valueOf(animalTypeUser.toUpperCase());
                break;
              }

              System.out.println("Введите имя животного");
              String animalNameUser = console.next().trim();

              animalToUpdate.setName(animalNameUser);

              while (true) {
                System.out.println("Введите возраст животного");
                String animalAgeUser = console.next().trim();

                if (!numberValidator.isNumber(animalAgeUser)) {
                  System.out.println("Введен некорректный возраст животного. Повторите снова");
                  continue;
                }
                animalAge = Integer.parseInt(animalAgeUser);

                animalToUpdate.setAge(animalAge);

                break;
              }

              while (true) {
                System.out.println("Введите вес животного");
                String animalWeightUser = console.next().trim();

                if (!numberValidator.isNumber(animalWeightUser)) {
                  System.out.println("Введен некорректный вес животного. Повторите снова");
                  continue;
                }
                animalWeight = Integer.parseInt(animalWeightUser);
                animalToUpdate.setWeight(animalWeight);

                break;
              }

              while (true) {
                System.out.println("Выберите цвет животного: white/black/orange");
                String animalColorUser = console.next().trim().toLowerCase();
                ColorData animalColor = ColorData.fromString(animalColorUser);

                if (!animalColorUser.equals("white") && !animalColorUser.equals("black") && !animalColorUser.equals("orange")) {
                  System.out.println("Цвет не найден, повторите снова");
                  continue;
                }
                colorData = ColorData.valueOf(animalColorUser.toUpperCase());
                animalToUpdate.setColor(colorData);
                break;
              }
              animalTable.updateAnimal(animalToUpdate);
              System.out.println(animalToUpdate.toString());
              break;

           } else {
              System.out.println("Животного с таким номером, в базе не существует, попробуйте еще раз");
            }
          }
          break;
        }

        case FILTER: {
          while (true) {
            System.out.println("Выберите тип животного: cat/dog/duck");
            String animalTypeUser = console.next().trim().toLowerCase();
            AnimalTypeData animalType = AnimalTypeData.fromString(animalTypeUser);

            if (!animalTypeUser.equals("cat") && !animalTypeUser.equals("dog") && !animalTypeUser.equals("duck")) {
              System.out.println("Животное не найдено, повторите снова");
              continue;
            }

            animals = animalTable.findFilter(animalTypeUser);

            System.out.println("\nНайденные животные типа " + animalTypeUser + ":");
            for (AbsAnimal animal : animals) {
              System.out.println(animal.toString());
            }

            break;
          }
          break;
        }

            case EXIT: {
              System.out.println("Пока пока!");
              console.close();
              return;
            }
          }
        }
      }
    }
