package JavaClasses;

import java.sql.SQLException;

public class Management{

    private static ServerCommunication serverCommunication = new ServerCommunication();

    public static String ConnectToTheServer(String database, String host, String port, String user, String password){
        serverCommunication.setDatabase(database);
        serverCommunication.setHost(host);
        serverCommunication.setPort(port);
        serverCommunication.setUser(user);
        serverCommunication.setPassword(password);
        try {
            String result  = serverCommunication.ConnectToTheServer();
            return result;
        } catch (ClassNotFoundException e) {
            return "Problem with driver: " + e.toString();
        } catch (SQLException e) {
            return "" + e.toString();
        } catch (IllegalAccessException e) {
            return "" + e.toString();
        } catch (InstantiationException e) {
            return "" + e.toString();
        }
    }

    public static void closeConnection(){
        try {
            serverCommunication.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
