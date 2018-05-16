package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    void initialize(){
        loadMenuScreen();
    }
    public void loadMenuScreen(){
        FXMLLoader loader = fxmlLoader("/gui/fxml/MenuWindow.fxml");

        MenuController menuController = loader.getController();
        menuController.setMainController(this);
    }

    public void loadDeliverScreen(){
        FXMLLoader loader = fxmlLoader("/gui/fxml/DelivShipWindow.fxml");

        DelivShipController deliverWindowController = loader.getController();
        deliverWindowController.setMainController(this);
    }

    public void loadAvailabilityScreen(){
        FXMLLoader loader = fxmlLoader("/gui/fxml/AvailabilityWindow.fxml");

        AvailabilityController controller = loader.getController();
        controller.setMainController(this);
    }
    public void loadShipmentScreen(){
        FXMLLoader loader = fxmlLoader("/gui/fxml/ShipmentWindow.fxml");

        ShipmentController controller = loader.getController();
        controller.setMainController(this);
    }

    public void loadCreateScreen(){
        FXMLLoader loader = fxmlLoader("/gui/fxml/CreateWindow.fxml");

        CreateController controller = loader.getController();
        controller.setMainController(this);
    }

    public void loadEditScreen(){
        FXMLLoader loader = fxmlLoader("/gui/fxml/EditWindow.fxml");

        EditController editController = loader.getController();
        editController.setMainController(this);
    }

    public FXMLLoader fxmlLoader(String path){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setScreen(pane);
        return loader;
    }

    public void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

}
