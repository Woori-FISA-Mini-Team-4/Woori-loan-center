package org.woorifisa.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

  private static final String PROPERTIES_FILE = "jdbc.properties";

  public static Connection connect() {
    try (InputStream inputStream =
        DBConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
      if (inputStream != null) {
        Properties properties = new Properties();
        properties.load(inputStream);

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String username = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
      } else {
        System.err.println("파일을 찾을 수 없습니다.");
      }
    } catch (IOException | ClassNotFoundException | SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  public static void exit(ResultSet resultSet, Statement statement, Connection connection) {
    try {
      if (resultSet != null) {
        resultSet.close();
      }
      if (statement != null) {
        statement.close();
      }
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }
}
