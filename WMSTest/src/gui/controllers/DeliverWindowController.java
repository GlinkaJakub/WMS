package gui.controllers;

import JavaClasses.Management;
import JavaClasses.Product;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

import java.util.Scanner;


public class DeliverWindowController {

    private MainController mainController;

    @FXML
    private ListView<Product> productListView;

    @FXML
    private TextField idProductTextField;

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

    public void showMoreAboutProduct(MouseEvent event){

        Product product = productListView.getSelectionModel().getSelectedItems().get(0);
        nameLabel.setText(product.getName());
        idLabel.setText(product.getId());
        makerLabel.setText(product.getMaker());
        parametersLabel.setText(product.getWidth() + "x" + product.getHeight() + "x" + product.getLength() + ", weight: " + product.getWeight());
        groupLabel.setText(product.getGroup());
    }



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void cancel(){
        mainController.loadMenuScreen();
    }


}
