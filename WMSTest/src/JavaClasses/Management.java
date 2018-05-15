package JavaClasses;

import java.sql.SQLException;

public class Management{

    private static final Management instance = new Management();

    private Management(){
    }

    public static Management getInstance(){
        return instance;
    }

    private ServerCommunication serverCommunication = new ServerCommunication();

    public String ConnectToTheServer(String database, String host, String port, String user, String password){
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

    public void closeConnection(){
        try {
            serverCommunication.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProduct(int id){
        Product product = new Product();
        try {
            product = serverCommunication.getProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

}
