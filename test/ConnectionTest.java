import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.assertNotNull;

public class ConnectionTest {

    @Test
    void testDataBase(){
        try(  Connection connection = DriverManager
                .getConnection(
                        "jdbc:mysql://localhost/elitespringbootclass?createDatabaseIfNotExist=true",
                        "root","nedstark");){

            assertNotNull(connection);
            System.out.println(connection);
        }catch (SQLException exception){
            exception.printStackTrace();
            System.err.println("ERROR connecting to database-> "+ exception.getMessage());
        }

    }
}
