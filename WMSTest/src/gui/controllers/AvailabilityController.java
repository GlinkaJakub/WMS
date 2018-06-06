package gui.controllers;

import JavaClasses.Management;
import JavaClasses.ProductCard;
import JavaClasses.Sector;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AvailabilityController {

    public ChoiceBox<Sector> sectorChoiceBox;
    public ChoiceBox<JavaClasses.Rack> rackChoiceBox;
    public ChoiceBox<JavaClasses.Shelf> shelfChoiceBox;
    public Label sectorSpace;
    public Label rackSpace;
    public Label shelfSpace;
    @FXML
    private ListView<ProductCard> productsOnShelf;
    private MainController mainController;
    private Management management;
    private ObservableList<ProductCard> observableList;

    public void initialize() {
        management = Management.getInstance();

        ListProperty<ProductCard> listProperty = new SimpleListProperty<>();
        observableList = FXCollections.observableArrayList();
        listProperty.set(observableList);
        productsOnShelf.itemsProperty().bindBidirectional(listProperty);

        sectorChoiceBox.setItems(FXCollections.observableArrayList(management.getSectors()));
        sectorChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> {
                    try {
                        rackChoiceBox.setItems(FXCollections.observableArrayList(management.getRack(newValue)));
                        sectorSpace.setText(management.getAvailability(newValue));
                    } catch (NullPointerException e) {
                    }
                }
        );
        rackChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    try {
                        shelfChoiceBox.setItems(FXCollections.observableArrayList(management.getShelves(newValue)));
                        rackSpace.setText(management.getAvailability(newValue));
                    } catch (NullPointerException e) {
                    }
                })
        );

        shelfChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (((observable, oldValue, newValue) -> {
                    try {
                        shelfSpace.setText(management.getAvailability(newValue));
                        observableList.clear();
                        observableList.addAll(management.getProductOnShelf(newValue.getId()));
                        productsOnShelf.refresh();
                    } catch (NullPointerException e) {
                    }
                }))
        );

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void back() {
        mainController.loadMenuScreen();
    }
}
