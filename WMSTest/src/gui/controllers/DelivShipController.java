package gui.controllers;

import JavaClasses.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;


public class DelivShipController {

    private MainController mainController;

    @FXML
    private Label finalContractorLabel;

    @FXML
    private Button addButton;

    @FXML
    private TextField quantityTextField;

    @FXML
    private ListView<Product> productListView;

    @FXML
    private ListView<Product> productsToDeliverView;

    @FXML
    private ListView<Contractor> providerListView;

    @FXML
    private TextField searchProduct;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label makerLabel;

    @FXML
    private Label parametersLabel;

    @FXML
    private Label groupLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label contractorNameLabel;

    @FXML
    private Label email;

    @FXML
    private Label address;

    @FXML
    private Label phone;

    @FXML
    private TextField identifyContractorTextField;

    @FXML
    private CheckBox isDeliver;

    @FXML
    private CheckBox isShipment;

    @FXML
    private Label contractorLabel;

    private ObservableList<Product> searchingListProduct;

    private ObservableList<Product> productsToDeliver;

    private ObservableList<Contractor> searchingListContractor;

    private List<Integer> quantityProducts = new ArrayList<>();

    private Contractor finalContractor = new Contractor();

    @FXML
    void initialize() {

        isDeliver.selectedProperty().addListener(((observable, oldValue, newValue) -> {

            if (newValue) {
                isShipment.setSelected(false);
                searchingListContractor.clear();
                contractorLabel.setText("Provider");
                identifyContractorTextField.setText("");

            } else
                isShipment.setSelected(true);
        }));

        isShipment.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                isDeliver.setSelected(false);
                contractorLabel.setText("Client");
            } else
                isDeliver.setSelected(true);
        });


        ListProperty<Product> listProperty = new SimpleListProperty<>();
        searchingListProduct = FXCollections.observableArrayList();
        listProperty.set(searchingListProduct);
        productListView.itemsProperty().bindBidirectional(listProperty);

        ListProperty<Product> deliverListProperty = new SimpleListProperty<>();
        productsToDeliver = FXCollections.observableArrayList();
        deliverListProperty.set(productsToDeliver);
        productsToDeliverView.itemsProperty().bindBidirectional(deliverListProperty);

        ListProperty<Contractor> providerListProperty = new SimpleListProperty<>();
        searchingListContractor = FXCollections.observableArrayList();
        providerListProperty.set(searchingListContractor);
        providerListView.itemsProperty().bindBidirectional(providerListProperty);

        quantityTextField.disableProperty().bind(idLabel.textProperty().isEmpty());
        addButton.disableProperty().bind(quantityTextField.textProperty().isEmpty());

        getProduct(searchProduct.getText());

        searchProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            searchingListProduct.clear();
            getProduct(newValue);
        });

        contractorLabel.textProperty().addListener(((observable, oldValue, newValue) -> {
            identifyContractorTextField.setText("");
            getContractor("");
        }));

        getContractor(identifyContractorTextField.getText());
        identifyContractorTextField.textProperty().addListener(((observable, oldValue, newValue) -> {

            searchingListContractor.clear();
            getContractor(newValue);

            if (newValue.isEmpty()) {
                contractorNameLabel.setText("");
                address.setText("");
                phone.setText("");
                email.setText("");
            }
        }));
    }

    private void getContractor(String newValue) {
        searchingListContractor.clear();
        if (isDeliver.isSelected()) {
            getProvider(newValue);
        } else {
            getClient(newValue);
        }
    }

    private void getProvider(String id) {
        Management management = Management.getInstance();
        List<Provider> providers = management.getProvider(id);
        searchingListContractor.addAll(providers);
    }

    private void getClient(String id) {
        Management management = Management.getInstance();
        List<Client> clients = management.getClient(id);
        searchingListContractor.addAll(clients);
    }

    private void getProduct(String id) {
        Management management = Management.getInstance();
        List<Product> productList = management.getProduct(id);
        searchingListProduct.addAll(productList);
    }

    public void showMoreAboutProduct() {
        productsToDeliverView.getSelectionModel().clearSelection();
        quantityLabel.setText("");
        Product product = productListView.getSelectionModel().getSelectedItem();
        nameLabel.setText(product.getName());
        idLabel.setText(product.getId());
        makerLabel.setText(product.getMaker());
        parametersLabel.setText(product.getWidth() + "x" + product.getHeight() + "x" + product.getLength() + ", weight: " + product.getWeight());
        groupLabel.setText(product.getGroup());
    }

    public void showMoreAboutContractor() {
        Contractor contractor = providerListView.getSelectionModel().getSelectedItem();
        contractorNameLabel.setText(contractor.getName() + ", " + contractor.getNip());
        phone.setText(contractor.getPhone());
        address.setText(contractor.getCity() + " " + contractor.getStreet() + " st. " + contractor.getPostCode() + " " + contractor.getCity());
        email.setText(contractor.getEmail());
    }

    public void addProvider() {
        finalContractor = providerListView.getSelectionModel().getSelectedItem();
        finalContractorLabel.setText(finalContractor.toString());
    }

    public void addToList() {
        int index = productsToDeliver.indexOf(productListView.getSelectionModel().getSelectedItem());
        if (index >= 0) {
            quantityProducts.remove(index);
            productsToDeliver.remove(index);
        }

        productsToDeliver.add(productListView.getSelectionModel().getSelectedItem());
        quantityProducts.add(Integer.parseInt(quantityTextField.getText()));
        quantityLabel.setText(quantityTextField.getText());
    }

    public void showMoreAboutDeliverProduct() {
        productListView.getSelectionModel().clearSelection();
        Product product = productsToDeliverView.getSelectionModel().getSelectedItem();
        nameLabel.setText(product.getName());
        idLabel.setText(product.getId());
        makerLabel.setText(product.getMaker());
        parametersLabel.setText(product.getWidth() + "x" + product.getHeight() + "x" + product.getLength() + ", weight: " + product.getWeight());
        groupLabel.setText(product.getGroup());
        quantityLabel.setText(quantityProducts.get(productsToDeliver.indexOf(product)).toString());
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void realize() {
        Management management = Management.getInstance();
        if (isDeliver.isSelected())
            management.realizeDeliver(productsToDeliver, quantityProducts, finalContractor);
        else
            management.realizeShipment(productsToDeliver, quantityProducts, finalContractor);
        productListView.getSelectionModel().clearSelection();
        providerListView.getSelectionModel().clearSelection();
        contractorLabel.setText("");
        productsToDeliver.clear();
    }

    public void removeProduct(){
        productsToDeliver.remove(productsToDeliverView .getSelectionModel().getSelectedItem());

    }

    public void cancel() {
        mainController.loadMenuScreen();
    }


}
