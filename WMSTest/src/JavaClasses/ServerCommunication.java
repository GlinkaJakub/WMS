package JavaClasses;

import java.sql.*;

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

    public Product getProduct(int id) throws SQLException {
        Product product = null;
        ResultSet rs = statement.executeQuery("EXEC getProduct @" + id + " ");
        rs.next();
        product.setName(rs.getString("name"));
        product.setGroup(rs.getString("group"));
        product.setMaker(rs.getString("maker"));
        product.setWidth(Integer.parseInt(rs.getString("width")));
        product.setHeight(Integer.parseInt(rs.getString("height")));
        product.setLength(Integer.parseInt(rs.getString("length")));
        product.setWeight(Integer.parseInt(rs.getString("weight")));
        return product;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
