package dao;

import animals.AbsAnimal;
import connector.MySqlDbConnector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsTable {
  protected String tableName;
  protected Map<String, String> columns = new HashMap<>();

  public AbsTable(String tableName) {
    this.tableName = tableName;
  }

  public void create() {
    String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)",
        this.tableName, convertMapColumnsToString());
    try {
      new MySqlDbConnector().execute(sqlRequest);
    } catch (IOException | SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private String convertMapColumnsToString() {
    String result = "";
    for (Map.Entry<String, String> el : columns.entrySet()) {
      result += el.getKey() + " " + el.getValue() + ",";
    }
    result = result.substring(0, result.length() - 1);
    return result;
  }
}

