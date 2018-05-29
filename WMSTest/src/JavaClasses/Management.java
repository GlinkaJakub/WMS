package JavaClasses;

import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Management {

    private static final Management instance = new Management();

    private Management() {
    }

    public static Management getInstance() {
        return instance;
    }

    private ServerCommunication serverCommunication = new ServerCommunication();

    public String ConnectToTheServer(String database, String host, String port, String user, String password) {
        serverCommunication.setDatabase(database);
        serverCommunication.setHost(host);
        serverCommunication.setPort(port);
        serverCommunication.setUser(user);
        serverCommunication.setPassword(password);
        try {
            String result = serverCommunication.ConnectToTheServer();
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


    public void closeConnection() {
        try {
            serverCommunication.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProduct(String id) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = serverCommunication.getProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<ProductCard> getProductCards(String id) {
        List<ProductCard> productCards = new ArrayList<>();
        try{
            productCards = serverCommunication.getProductCard(id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return productCards;
    }

    public List<Provider> getProvider(String id) {

        List<Provider> providerList = new ArrayList<>();
        try {
            providerList = serverCommunication.getProvider(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providerList;
    }

    public List<Client> getClient(String id) {
        List<Client> clientList = new ArrayList<>();
        try {
            clientList = serverCommunication.getClient(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    public void addProduct(Product product) {
        try {
            serverCommunication.addProduct(product);
        } catch (SQLException e) {
        }
    }

    public void addGroup(ProductGroup productGroup) {
        try {
            serverCommunication.addGroup(productGroup);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void addContractor(Contractor contractor) {
        try {
            if (contractor instanceof Provider)
                serverCommunication.addProvider((Provider) contractor);
            else
                serverCommunication.addClient((Client) contractor);
        } catch (SQLException e) {
        }
    }


    public void realizeDeliver(List<Product> productsToDeliver, List<Integer> quantityProducts, Contractor finalContractor) {

        for (int i = 0; i < productsToDeliver.size(); i++) {
            try {
                for (int j = 0; j < quantityProducts.get(i); j++)
                    serverCommunication.putOnShelf(productsToDeliver.get(i), finalContractor);
            } catch (SQLException e) {
            }
        }
    }

    public void realizeShipment(ObservableList<Product> productsToDeliver, List<Integer> quantityProducts, Contractor finalContractor) {
        for (int i = 0; i < productsToDeliver.size(); i++) {
            for (int j = 0; j < quantityProducts.get(i); j++)
                serverCommunication.getProductsOut(productsToDeliver.get(i), finalContractor);
        }
    }

    public void addSector() {
        try {
            serverCommunication.addSector();
        } catch (SQLException e) {
        }
    }

    public void addRack(String sectorId, String rackType) {
        try {
            serverCommunication.addRack(Integer.valueOf(sectorId), Integer.valueOf(rackType));
        }catch (SQLException e){
        }
    }

    public void addRackType(RackType rackType) {
        try{
            serverCommunication.addRackType(rackType);
        }catch(SQLException e){
        }
    }

    public List<ProductCard> getProductCard(String id) {
        List<ProductCard> productCards = null;
        try {
            productCards = serverCommunication.getProductCard(id);
        } catch (SQLException e) {
        }
        return productCards;
    }

    public List<Sector> getSectors() {
        try {
            return serverCommunication.getSectors();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Rack> getRack(Sector sector) {
        List<Rack> racks = new ArrayList<>();
        if(sector == null)
            return null;
        try{
            racks = serverCommunication.getRack(Integer.valueOf(sector.getId()));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return racks;
    }

    public List<RackType> getRackType() {
        List<RackType> rackTypes = new ArrayList<>();
        try{
            rackTypes = serverCommunication.getRackTypes();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rackTypes;
    }
}
