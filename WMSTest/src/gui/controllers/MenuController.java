package gui.controllers;

import JavaClasses.Facade;
import JavaClasses.GUI;
import JavaClasses.ServerCommunication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MenuController {

    @FXML
    private StackPane menuStackPane;

    private MainController mainController;


    public void logOut() {
        Stage previousStage = (Stage) menuStackPane.getScene().getWindow();
        previousStage.close();
        Facade.closeConnection();

        GUI gui = new GUI();
        Stage stage = new Stage();
        try {
            gui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void exit(){
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
