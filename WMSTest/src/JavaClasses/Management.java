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

    public String connectToTheServer(String database, String host, String port, String user, String password) {
        serverCommunication.setDatabase(database);
        serverCommunication.setHost(host);
        serverCommunication.setPort(port);
        serverCommunication.setUser(user);
        serverCommunication.setPassword(password);
        try {
            return serverCommunication.ConnectToTheServer();
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
            for (int j = 0; j < quantityProducts.get(i); j++) {
                getProductOut(productsToDeliver.get(i), finalContractor);
            }
        }
    }

    public void getProductOut(Product productsToRemove, Contractor finalContractor) {
        try {
            serverCommunication.getProductsOut(productsToRemove, finalContractor);
        } catch (SQLException e) {
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

    public void update(Provider provider) {
        try {
            serverCommunication.update(provider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ProductCard productCard) {
        try {
            serverCommunication.update(productCard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Product product) {
        try {
            serverCommunication.update(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Client client) {
        try {
            serverCommunication.update(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Provider provider) {
        try {
            serverCommunication.remove(provider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Client client) {
        try {
            serverCommunication.remove(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Product product) {
        try {
            serverCommunication.remove(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(ProductCard productCard) {
        try {
            serverCommunication.remove(productCard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Shelf> getShelves(Rack rack) {
        List<Shelf> shelfList = new ArrayList<>();
        try {
            shelfList = serverCommunication.getShelves(rack.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shelfList;
    }

    public String getAvailability(Object place) {
        int availability = 0;
        try {
            if (place instanceof Sector)
                availability = serverCommunication.getAvailability((Sector) place);
            else if (place instanceof Rack)
                availability = serverCommunication.getAvailability((Rack) place);
            else if (place instanceof Shelf)
                availability = serverCommunication.getAvailability((Shelf) place);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availability + "";
    }

    public List<ProductCard> getProductOnShelf(String id) {
        List<ProductCard> productCardList = null;
        try {
            productCardList = serverCommunication.getProductOnShelf(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCardList;
    }
}
