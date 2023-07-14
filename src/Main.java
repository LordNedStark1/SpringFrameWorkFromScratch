import java.sql.*;

public class Main {
    public static void main(String[] args) {
      try(  Connection connection = DriverManager
                .getConnection(
                        "jdbc:mysql://localhost/elitesASpringBootClass?createDatabaseIfNotExist=true",
                        "root","nedstark");){
          Account account = new Account("BEN NED");

          createTable(connection);

          save(connection, account);
          Account foundAccount = findById(connection, 1);
          System.out.println(foundAccount);


      }catch (SQLException exception){
          System.err.println("ERROR connecting to database-> "+ exception.getMessage());
      }

    }

    private static Account findById(Connection connection, int id) throws SQLException {
        var statement = connection.prepareStatement("SELECT * FROM accounts WHERE id=?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Account account = null;
        if (resultSet.next()){
            account = new Account(resultSet.getString(1), resultSet.getInt(2));
        }
        return account;
    }

    private static Account save(Connection connection, Account account)throws  SQLException{
        String sql = "INSERT into account (name, id) values(?, NULL)";
        PreparedStatement insertStatement = connection.prepareStatement(sql);
        insertStatement.setString(1, account.getName());
        insertStatement.execute();

        return account;
    }

    private static void createTable(Connection connection)throws SQLException{
        String createTablesql = """
                  CREATE TABLE `account`(
                  `name` varchar(255) DEFAULT NULL,
                  `id` int NOT NULL AUTO_GEBERATE, 
                  PRIMARY KET (`id`)
                  );
                  """;

        PreparedStatement statement = connection.prepareStatement(createTablesql);
        statement.execute();
    }
}
