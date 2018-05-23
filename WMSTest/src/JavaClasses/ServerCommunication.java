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


    public ServerCommunication() {

    }

    public String ConnectToTheServer() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {

        String databaseURL = "jdbc:sqlserver://" +
                host + "\\MSSQLSERVER:" +
                port + ";databaseName=" +
                database;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

        connection = DriverManager.getConnection(databaseURL, user, password);
        statement = connection.createStatement();
        return "Success";
    }

    public void closeConnection() throws SQLException {

        connection.close();
    }

    public List<Product> getProduct(String id) throws SQLException {
        List<Product> productList = new ArrayList<>();
        ResultSet rs = statement.executeQuery("EXEC getProductLike '" + id + "' ");
        while (rs.next()) {
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
        while (rs.next()) {
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

    public List<Client> getClient(String id) throws SQLException {
        List<Client> clientList = new ArrayList<>();
        ResultSet rs = statement.executeQuery("EXEC getClientLike '" + id + "' ");
        while (rs.next()) {
            Client client = new Client();
            client.setName(rs.getString("name"));
            client.setNip(rs.getString("nip"));
            client.setPhone(rs.getString("phoneNumber"));
            client.setBuildingNumber(rs.getString("buildingnumber"));
            client.setCity(rs.getString("city"));
            client.setEmail(rs.getString("email"));
            client.setPostCode(rs.getString("postcode"));
            client.setStreet(rs.getString("street"));
            clientList.add(client);
        }
        return clientList;
    }


    public void addProduct(Product product) throws SQLException {
        statement.executeQuery("EXEC AddProduct '" + product.getName() +
                "', '" + product.getGroup() + "', '" + product.getMaker() +
                "', " + product.getWidth() + ", " + product.getHeight() +
                ", " + product.getLength() + ", " + product.getWeight() + " ");
    }

    public void addGroup(ProductGroup productGroup) throws SQLException {
        statement.executeQuery("EXEC AddGroup '" + productGroup.getName() + "' ");
    }

    public void addProvider(Provider provider) throws SQLException {
        statement.executeQuery("EXEC AddProvider '" + provider.getName() +
                "', '" + provider.getNip() + "', '" + provider.getCity() +
                "', '" + provider.getPostCode() + "', '" + provider.getStreet() +
                "', '" + provider.getBuildingNumber() + "', '" + provider.getPhone() +
                "', '" + provider.getEmail() + "' "
        );
    }

    public void addClient(Client client) throws SQLException {
        statement.executeQuery("EXEC AddClient '" + client.getName() +
                "', '" + client.getNip() + "', '" + client.getCity() +
                "', '" + client.getPostCode() + "', '" + client.getStreet() +
                "', '" + client.getBuildingNumber() + "', '" + client.getPhone() +
                "', '" + client.getEmail() + "' "
        );
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

    public void putOnShelf(Product product, Contractor finalContractor) throws SQLException {
        statement.executeQuery("EXEC PutOnShelf '" + product.getId() + "'");
    }

    public void getProductsOut(Product product, Contractor finalContractor) {

    }

    public List<ProductCard> getProductCard(String id) throws SQLException {
        List<ProductCard> productCardList = new ArrayList<>();
        ResultSet rs = statement.executeQuery("EXEC getProductCard '" + id + "' ");
        while (rs.next()) {
            ProductCard productCard = new ProductCard();
            productCard.setId(rs.getString("id_product"));
            productCard.setName(rs.getString("name"));
            productCard.setPlaceId(rs.getString("placement"));
            productCardList.add(productCard);
        }
        return productCardList;
    }

    public void addSector() throws SQLException {
        ResultSet rs = statement.executeQuery("EXEC addSector ");
    }

    public List<Sector> getSectors() throws SQLException {
        List<Sector> sectors = new ArrayList<>();
        ResultSet rs = statement.executeQuery("EXEC getSectors");
        while (rs.next()) {
            Sector sector = new Sector();
            sector.setId(rs.getString("id_sector"));
            sector.setRemainingSpace(rs.getInt("remainingspace"));
            sector.setSpace(rs.getInt("totalspace"));
            sectors.add(sector);
        }
        return sectors;
    }
}
