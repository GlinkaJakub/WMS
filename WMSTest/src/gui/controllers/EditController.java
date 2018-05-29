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

    @FXML
    public Label label1;

    @FXML
    public Label label2;

    @FXML
    public Label label3;

    @FXML
    public Label label4;

    @FXML
    public Label label5;
    public Label label00;
    public Label label01;
    public Label label02;
    public Label label03;
    public Label label04;

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
    public void openProductList() {
    }

    @FXML
    public void openProductCardList() {
    }

    @FXML
    public void openContractorList() {
    }

    @FXML
    public void cancel() {
        mainController.loadMenuScreen();
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOnAction() {
    }


    public void initialize() {
        ListProperty<Object> objects = new SimpleListProperty<>();
        editableObjects = FXCollections.observableArrayList();
        objects.set(editableObjects);
        editListView.itemsProperty().bindBidirectional(objects);
        productToggle.setSelected(true);
        addAllToList();

        searchTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            editableObjects.clear();
            addAllToList();
        }));

        toggleEdit.selectedToggleProperty().addListener(((observable, oldValue, newValue) -> {
            addAllToList();
            clearLabel();
        }));

    }

    private void clearLabel() {
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
    }

    public void showMore() {
        Object object = editListView.getSelectionModel().getSelectedItem();
        if (object instanceof Product) {
            Product product = (Product) object;
            label1.setText(product.getId());
            label2.setText(product.getName());
            label3.setText(product.getGroup());
            label4.setText(product.getMaker());
            label5.setText(product.getWidth() + "x" + product.getHeight() + "x" +
                    product.getLength() + ", weight: " + product.getWeight());
        } else if (object instanceof Contractor) {
            Contractor contractor = (Contractor) object;
            label1.setText(contractor.getName());
            label2.setText(contractor.getNip());
            label3.setText(contractor.getPhone());
            label4.setText(contractor.getEmail());
            label5.setText(contractor.getStreet() + " st. " + contractor.getBuildingNumber() +
                    " " + contractor.getPostCode() + " " + contractor.getCity());
        } else {
            ProductCard productCard = (ProductCard) object;
            label1.setText(String.valueOf(productCard.getId()));
            label2.setText(productCard.getName());
            label3.setText(productCard.getPlaceId());
            label4.setText("");
            label5.setText("");
        }
    }

    private void addAllToList() {
        editableObjects.clear();
        if (productToggle.isSelected()) {

            editableObjects.addAll(management.getProduct(searchTextField.getText()));
            label00.setText("ID");
            label01.setText("Name");
            label02.setText("Group");
            label03.setText("Maker");
            label04.setText("Parameters");
        } else if (contractorToggle.isSelected()) {

            editableObjects.addAll(management.getClient(searchTextField.getText()));
            editableObjects.addAll(management.getProvider(searchTextField.getText()));
            label00.setText("Name");
            label01.setText("NIP");
            label02.setText("Phone");
            label03.setText("E-mail");
            label04.setText("Address");
        } else if (productCardToggle.isSelected()) {

            editableObjects.addAll(management.getProductCard(searchTextField.getText()));
            label00.setText("ID");
            label01.setText("Name");
            label02.setText("Place ID");
            label03.setText("");
            label04.setText("");
        }
    }

}
