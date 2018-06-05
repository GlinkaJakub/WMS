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
    private int updateId;

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

    public List<ProductCard> getProductCard(String id) throws SQLException{
        List<ProductCard> productCards = new ArrayList<>();
        ResultSet rs = statement.executeQuery("EXEC getProductCard '" + id + "' ");
        while(rs.next()){
            ProductCard productCard = new ProductCard();
            productCard.setId(rs.getInt("id_card"));
            productCard.setProductId(rs.getInt("id_product"));
            productCard.setName(rs.getString("name"));
            productCard.setPlaceId(rs.getString("placement"));
            productCards.add(productCard);
        }
        return productCards;
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
    public List<Rack> getRack(int sectorId) throws SQLException{
        List<Rack> racks = new ArrayList<>();
        ResultSet rs = statement.executeQuery("EXEC getRack " + sectorId + " ");
        while (rs.next()){
            Rack rack = new Rack();
            rack.setId(rs.getInt("id_rack"));
            rack.setSectorId(rs.getInt("id_sector"));
            rack.setRackTypeId(rs.getInt("id_racktype"));
            rack.setRemainingSpace(rs.getInt("remainingspace"));
            rack.setTotalSpace(rs.getInt("totalspace"));
            racks.add(rack);
        }
        return racks;
    }



    public void addProduct(Product product) throws SQLException {
        ResultSet rs = statement.executeQuery("EXEC AddProduct '" + product.getName() +
                "', '" + product.getGroup() + "', '" + product.getMaker() +
                "', " + product.getWidth() + ", " + product.getHeight() +
                ", " + product.getLength() + ", " + product.getWeight() + " ");
        rs.next();
        updateId = rs.getInt(1);
    }

    public void addGroup(ProductGroup productGroup) throws SQLException {
        statement.executeQuery("EXEC AddGroup '" + productGroup.getName() + "' ");
    }

    public void addProvider(Provider provider) throws SQLException {
        ResultSet rs = statement.executeQuery("EXEC AddProvider '" + provider.getName() +
                "', '" + provider.getNip() + "', '" + provider.getCity() +
                "', '" + provider.getPostCode() + "', '" + provider.getStreet() +
                "', '" + provider.getBuildingNumber() + "', '" + provider.getPhone() +
                "', '" + provider.getEmail() + "' "
        );
        rs.next();
        updateId = rs.getInt(1);
    }

    public void addClient(Client client) throws SQLException {
        ResultSet rs = statement.executeQuery("EXEC AddClient '" + client.getName() +
                "', '" + client.getNip() + "', '" + client.getCity() +
                "', '" + client.getPostCode() + "', '" + client.getStreet() +
                "', '" + client.getBuildingNumber() + "', '" + client.getPhone() +
                "', '" + client.getEmail() + "' "
        );
        rs.next();
        updateId = rs.getInt(1);
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
        ResultSet rs = statement.executeQuery("EXEC PutOnShelf " + product.getId() + " ");
        //statement.executeQuery("EXEC addLedgerEntry " + product.getId() + " '" + finalContractor.getNip() + "'" + " 1 " + " " + rs.getInt(0));
    }

    public void getProductsOut(Product product, Contractor finalContractor) throws SQLException {
        statement.executeQuery(("EXEC getProductOut " + product.getId() + " "));
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

    public void addRackType(RackType rackType) throws SQLException {
        ResultSet rs = statement.executeQuery("EXEC addRackType " + rackType.getWidth() + ", " + rackType.getLength()+
            ", " + rackType.getShelfNumber() + ", " + rackType.getSpace() + " ");
    }

    public void addRack(Integer sectorId, Integer rackType) throws SQLException {
        ResultSet rs = statement.executeQuery("EXEC addRack " + sectorId + ", " + rackType + " ");
    }

    public List<RackType> getRackTypes() throws SQLException {
        List<RackType> rackTypes = new ArrayList<>();
        ResultSet rs = statement.executeQuery("EXEC getRackType ");
        while(rs.next()){
            RackType rackType = new RackType();
            rackType.setId(rs.getInt("id_racktype"));
            rackType.setWidth(rs.getInt("width"));
            rackType.setLength(rs.getInt("length"));
            rackType.setShelfNumber(rs.getInt("shelfNumber"));
            rackType.setSpace(rs.getInt("space"));
            rackTypes.add(rackType);
        }
        return rackTypes;
    }

    public void update(Provider provider) throws SQLException {
        List<Provider> providerList = getProvider(provider.getNip());
        for (Provider prov : providerList)
            if (provider.getNip().equals(prov.getNip())) {
                addProvider(provider);
                ResultSet rs = statement.executeQuery("Exec ArchiveProvider " + provider.getNip() + ", " + updateId);
            }
    }

    public void update(ProductCard productCard) throws SQLException {
        List<ProductCard> productCards = getProductCard(String.valueOf(productCard.getId()));
        for (ProductCard pc : productCards)
            if (productCard.getId() == pc.getId()) {
                addProductCard(productCard);
                ResultSet rs = statement.executeQuery("Exec ArchiveProductCard " + pc.getId() + ", " + updateId);
            }
    }

    private void addProductCard(ProductCard productCard) throws SQLException {
        ResultSet rs = statement.executeQuery("EXEC addProductCard " + productCard.getProductId() + ", " + productCard.getPlaceId() + " ");
        rs.next();
        updateId = rs.getInt(1);
    }

    public void update(Product product) throws SQLException {
        List<Product> products = getProduct(product.getId());
        for (Product p : products)
            if (product.getId().equals(p.getId())) {
                addProduct(product);
                System.out.println(updateId);
                ResultSet rs = statement.executeQuery("Exec ArchiveProduct " + product.getId() + ", " + updateId);
            }
    }

    public void update(Client client) throws SQLException {
        List<Client> clientList = getClient(client.getNip());
        for (Client c : clientList)
            if (client.getNip().equals(c.getNip())) {
                addClient(client);
                ResultSet rs = statement.executeQuery("Exec ArchiveClient " + client.getNip() + ", " + updateId);
            }
    }

    public void remove(Provider provider) throws SQLException {
        statement.executeQuery(("EXEC ArchiveProvider " + provider.getNip() + ", " + updateId));
    }

    public void remove(Client client) throws SQLException {
        statement.executeQuery(("EXEC ArchiveClient " + client.getNip() + ", " + updateId));
    }

    public void remove(Product product) throws SQLException {
        statement.executeQuery(("EXEC ArchiveProduct " + product.getId() + ", " + updateId));
    }

    public void remove(ProductCard productCard) throws SQLException {
        statement.executeQuery(("EXEC ArchiveProductCard " + productCard.getId() + ", " + updateId));
    }
}
