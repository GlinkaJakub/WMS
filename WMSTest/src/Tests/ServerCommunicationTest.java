package Tests;

import JavaClasses.ServerCommunication;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class ServerCommunicationTest {

    @Test
    public void getProduct() {
        ServerCommunication serverCommunication = new ServerCommunication();
        try {
            Assert.assertTrue(serverCommunication.ConnectToTheServer().equals("Success"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }
}
