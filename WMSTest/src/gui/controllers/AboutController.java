package gui.controllers;

public class AboutController {
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void back() {
        mainController.loadMenuScreen();
    }
}
