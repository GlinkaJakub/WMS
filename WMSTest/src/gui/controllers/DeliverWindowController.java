package gui.controllers;

public class DeliverWindowController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void cancel(){
        mainController.loadMenuScreen();
    }
}
