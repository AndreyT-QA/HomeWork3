package dao;

import animals.AbsAnimal;
import connector.MySqlDbConnector;
import data.AnimalTypeData;
import data.ColorData;
import factory.AnimalFactory;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable implements IAnimalTable{
  public AnimalTable() {
    super("animals");
    columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
    columns.put("type", "varchar(15)");
    columns.put("name", "varchar(15)");
    columns.put("age", "int");
    columns.put("weight", "int");
    columns.put("color", "varchar(15)");
    create();
  }

  public List<AbsAnimal> findAll() {
    List<AbsAnimal> animals = new ArrayList<>();
    try (ResultSet rs = new MySqlDbConnector().executeQuery("SELECT * FROM "
        + tableName)) {
      while (rs.next()) {
        long id = rs.getLong("id");
        String type = rs.getString("type");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        int weight = rs.getInt("weight");
        String color = rs.getString("color");

        AnimalTypeData animalType = AnimalTypeData.valueOf(type.trim().toUpperCase());
        ColorData colorData = ColorData.valueOf(color.trim().toUpperCase());

        animals.add(new AnimalFactory(id, type, name, age, weight, colorData).create(animalType));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return animals;
  }

  public AbsAnimal findById(Long searchId) {
    AbsAnimal findAnimal = null;
    try (ResultSet rs = new MySqlDbConnector().executeQuery(
        "SELECT * FROM " + tableName + " WHERE id=" + searchId)) {
      while (rs.next()) {
        long id = rs.getLong("id");
        String type = rs.getString("type");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        int weight = rs.getInt("weight");
        String color = rs.getString("color");

        AnimalTypeData animalType = AnimalTypeData.valueOf(type.trim().toUpperCase());
        ColorData colorData = ColorData.valueOf(color.trim().toUpperCase());

        findAnimal = new AnimalFactory(id, type, name, age, weight, colorData).create(animalType);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return findAnimal;
  }

  public void insertAnimal(AbsAnimal animal) {
    String insert = "INSERT INTO " + tableName
        + " (type, name, age, weight, color) VALUES ('"
        + animal.getType() + "', '"
        + animal.getName() + "', "
        + animal.getAge() + ", "
        + animal.getWeight() + ", '"
        + animal.getColor() + "')";
    try {
      new MySqlDbConnector().executeUpdate(insert);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void updateAnimal(AbsAnimal animal) {
    String update = "UPDATE " + tableName
        + " SET type='" + animal.getType() + "',"
        + " name='" + animal.getName() + "', "
        + "age=" + animal.getAge() + ", "
        + "weight=" + animal.getWeight() + ", "
        + "color='" + animal.getColor()
        + "' WHERE id=" + animal.getId();
    try {
      new MySqlDbConnector().executeUpdate(update);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public List<AbsAnimal> findFilter(String AnimalTypeFilter) {
    List<AbsAnimal> animals = new ArrayList<>();
    try (ResultSet rs = new MySqlDbConnector().executeQuery("SELECT * FROM "
        + tableName + " WHERE type='" + AnimalTypeFilter + "'")) {
      while (rs.next()) {
        long id = rs.getLong("id");
        String type = rs.getString("type");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        int weight = rs.getInt("weight");
        String color = rs.getString("color");

        AnimalTypeData animalType = AnimalTypeData.valueOf(type.trim().toUpperCase());
        ColorData colorData = ColorData.valueOf(color.trim().toUpperCase());

        animals.add(new AnimalFactory(id, type, name, age, weight, colorData).create(animalType));

      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return animals;
  }
}






