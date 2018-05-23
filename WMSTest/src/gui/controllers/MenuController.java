package gui.controllers;

import JavaClasses.GUI;
import JavaClasses.Management;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuController {


    @FXML
    private StackPane menuStackPane;

    private MainController mainController;


    public void logOut() {
        Management management = Management.getInstance();
        Stage previousStage = (Stage) menuStackPane.getScene().getWindow();
        previousStage.close();
        management.closeConnection();

        GUI gui = new GUI();
        Stage stage = new Stage();
        try {
            gui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDeliverScreen() {
        mainController.loadDeliverScreen();
    }

    public void loadAvailabilityScreen() {
        mainController.loadAvailabilityScreen();
    }

    public void loadCreateScreen() {
        mainController.loadCreateScreen();
    }

    public void loadEditScreen() {
        mainController.loadEditScreen();
    }

    public void loadChangeScreen(){
        mainController.loadChangeScreen();
    }

    public void loadInfoScreen(){
        mainController.loadInfoScreen();
    }

    public void exit() {
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
