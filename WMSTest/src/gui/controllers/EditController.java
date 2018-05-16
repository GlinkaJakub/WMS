package gui.controllers;


import JavaClasses.Contractor;
import JavaClasses.Management;
import JavaClasses.Product;
import JavaClasses.ProductCard;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;


public class EditController {

    private Management management = Management.getInstance();

    @FXML
    private ListView<Object> editListView;

    private ObservableList<Object> editableObjects;

    private MainController mainController;

    @FXML
    private ToggleGroup toggleEdit;

    @FXML
    private ToggleButton productToggle;

    @FXML
    private ToggleButton productCardToggle;

    @FXML
    private ToggleButton contractorToggle;

    @FXML
    private TextField searchTextField;

    @FXML
    private Label editableObject;

    @FXML
    public void openProductList( ) {
    }

    @FXML
    public void openProductCardList( ) {
    }

    @FXML
    public void openContractorList( ) {
    }

    @FXML
    public void cancel(){
        mainController.loadMenuScreen();
    }

    @FXML
    public void doneButton( ) {
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOnAction( ) {
    }


    public void initialize(){
        ListProperty<Object> objects = new SimpleListProperty<>();
        editableObjects = FXCollections.observableArrayList();
        objects.set(editableObjects);
        editListView.itemsProperty().bindBidirectional(objects);

        searchTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(productToggle.isSelected())
                editableObjects.addAll(management.getProduct(searchTextField.getText()));
            else if(productCardToggle.isSelected())
                editableObjects.addAll(management.getProductCard(searchTextField.getText()));
            else if (contractorToggle.isSelected()) {
                editableObjects.addAll(management.getClient(searchTextField.getText()));
                editableObjects.addAll(management.getProvider(searchTextField.getText()));
            }
        }));

        toggleEdit.selectedToggleProperty().addListener(((observable, oldValue, newValue) -> {
            editableObjects.clear();
        }));
    }

}
