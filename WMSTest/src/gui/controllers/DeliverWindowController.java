package gui.controllers;

import JavaClasses.Management;
import JavaClasses.Product;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class DeliverWindowController {

    private MainController mainController;

    @FXML
    public ListView<Product> productListView;

    @FXML
    public TextField idProductTextField;

    private ObservableList<Product> observableListProduct;

    @FXML
    void initialize(){
        ListProperty<Product> listProperty = new SimpleListProperty<>();
        observableListProduct = FXCollections.observableArrayList();
        listProperty.set(observableListProduct);
        productListView.itemsProperty().bindBidirectional(listProperty);
    }

    public void getProduct(){
        Management management = Management.getInstance();
        Product product =  management.getProduct(Integer.parseInt(idProductTextField.getText()));
        observableListProduct.add(product);
    }



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void cancel(){
        mainController.loadMenuScreen();
    }


}
