package connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface IDBConnector {
  void execute(String sqlRequest) throws SQLException;
  ResultSet executeQuery(String sqlRequest) throws SQLException;
  void executeUpdate(String sqlRequest) throws SQLException;

  }
