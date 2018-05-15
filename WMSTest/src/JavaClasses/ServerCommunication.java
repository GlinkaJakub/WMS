package JavaClasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServerCommunication {

    private Connection connection = null;
    private Statement statement = null;
    private String host;
    private String port;
    private String database;


    private String user;
    private String password;


    public ServerCommunication(){

    }

    public String ConnectToTheServer() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {

        String databaseURL = "jdbc:sqlserver://"+
                host+"\\MSSQLSERVER:"+
                port+";databaseName="+
                database;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

        connection = DriverManager.getConnection(databaseURL,user,password);
        statement = connection.createStatement();
        return "Success";
    }

    public void closeConnection() throws SQLException {

            connection.close();
    }

    public List<Product> getProduct(String id) throws SQLException {
        List<Product> productList = new ArrayList<>();
        ResultSet rs = statement.executeQuery("EXEC getProductLike '" + id + "' ");
        while(rs.next()) {
            Product product = new Product();
            product.setId(rs.getString("id_product"));
            product.setName(rs.getString("name"));
            product.setGroup(rs.getString("productGroup"));
            product.setMaker(rs.getString("maker"));
            product.setWidth(rs.getInt("width"));
            product.setHeight(rs.getInt("height"));
            product.setLength(rs.getInt("length"));
            product.setWeight(rs.getInt("weight"));
            productList.add(product);
        }
        return productList;
    }
    public List<Provider> getProvider(String id) throws SQLException {
        List<Provider> providerList = new ArrayList<>();
        ResultSet rs = statement.executeQuery("EXEC getProviderLike '" + id + "' ");
        while(rs.next()) {
            Provider provider = new Provider();
            provider.setName(rs.getString("name"));
            provider.setNip(rs.getString("nip"));
            provider.setPhone(rs.getString("phoneNumber"));
            provider.setBuildingNumber(rs.getString("buildingnumber"));
            provider.setCity(rs.getString("city"));
            provider.setEmail(rs.getString("email"));
            provider.setPostCode(rs.getString("postcode"));
            provider.setStreet(rs.getString("street"));
            providerList.add(provider);
        }
        return providerList;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
