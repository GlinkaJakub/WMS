package gui.controllers;

public class AvailabilityController {

    MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void back(){
        mainController.loadMenuScreen();
    }
}
