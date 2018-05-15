package gui.controllers;

import JavaClasses.Management;
import JavaClasses.Product;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;


public class DeliverController {

    private MainController mainController;

    @FXML
    private Button addButton;

    @FXML
    private TextField quantityTextField;

    @FXML
    private ListView<Product> productListView;

    @FXML
    private ListView<Product> productsToDeliverView;

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

    private ObservableList<Product> observableSearchingList;

    private ObservableList<Product> productsToDeliver;

    private List<String> quantityProducts = new ArrayList<>();

    @FXML
    void initialize(){


        ListProperty<Product> listProperty = new SimpleListProperty<>();
        observableSearchingList = FXCollections.observableArrayList();
        listProperty.set(observableSearchingList);
        productListView.itemsProperty().bindBidirectional(listProperty);

        ListProperty<Product> deliverListProperty = new SimpleListProperty<>();
        productsToDeliver = FXCollections.observableArrayList();
        deliverListProperty.set(productsToDeliver);
        productsToDeliverView.itemsProperty().bindBidirectional(deliverListProperty);

        quantityTextField.disableProperty().bind(idLabel.textProperty().isEmpty());
        addButton.disableProperty().bind(quantityTextField.textProperty().isEmpty());

        searchProduct.textProperty().addListener((observable,oldValue,newValue)->{
            observableSearchingList.clear();
            if(newValue.length() > 0)
                getProduct(newValue);
        });
    }

    private void getProduct(String id){
        Management management = Management.getInstance();
        List<Product> productList =  management.getProduct(id);
        observableSearchingList.addAll(productList);
    }

    public void showMoreAboutProduct(){
        quantityLabel.setText("");
        Product product = productListView.getSelectionModel().getSelectedItem();
        nameLabel.setText(product.getName());
        idLabel.setText(product.getId());
        makerLabel.setText(product.getMaker());
        parametersLabel.setText(product.getWidth() + "x" + product.getHeight() + "x" + product.getLength() + ", weight: " + product.getWeight());
        groupLabel.setText(product.getGroup());
    }

    public void addToList(){
         quantityLabel.setText(quantityTextField.getText());
         productsToDeliver.add(productListView.getSelectionModel().getSelectedItem());
         quantityProducts.add(quantityTextField.getText());
    }

    public void showMoreAboutDeliverProduct(){
        Product product = productsToDeliverView.getSelectionModel().getSelectedItem();
        nameLabel.setText(product.getName());
        idLabel.setText(product.getId());
        makerLabel.setText(product.getMaker());
        parametersLabel.setText(product.getWidth() + "x" + product.getHeight() + "x" + product.getLength() + ", weight: " + product.getWeight());
        groupLabel.setText(product.getGroup());
        quantityLabel.setText(quantityProducts.get(productsToDeliver.indexOf(product)));
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void cancel(){
        mainController.loadMenuScreen();
    }


}
