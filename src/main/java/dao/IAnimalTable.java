package dao;

import animals.AbsAnimal;
import java.util.List;

public interface IAnimalTable {
  List<AbsAnimal> findAll();
  AbsAnimal findById(Long searchId);
  void insertAnimal(AbsAnimal animal);
  void updateAnimal(AbsAnimal animal);
  List<AbsAnimal> findFilter(String AnimalTypeFilter);
}
