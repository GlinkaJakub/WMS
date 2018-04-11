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
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/fxml/MenuWindow.fxml"));
        StackPane stackPane = null;
        try {
            stackPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setScreen(stackPane);
    }

    public void loadDeliverScreen(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/fxml/DeliverWindow.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DeliverWindowController deliverWindowController = loader.getController();
        deliverWindowController.setMainController(this);
        setScreen(pane);
    }

    public void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

}
