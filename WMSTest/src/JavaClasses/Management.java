package JavaClasses;

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


    public void realizeDeliver(ObservableList<Product> productsToDeliver, List<Integer> quantityProducts, Contractor finalContractor) {

        for (int i = 0; i < productsToDeliver.size(); i++) {
            try {
                for (int j = 0; j < quantityProducts.get(i); j++)
                    serverCommunication.putOnShelf(productsToDeliver.get(i), finalContractor);
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void addRack(String text) {
    }

    public void addRackType(RackType rackType) {

    }

    public List<ProductCard> getProductCard(String id) {
        List<ProductCard> productCards = null;
        try {
            productCards = serverCommunication.getProductCard(id);
        } catch (SQLException e) {
            e.printStackTrace();
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
}
