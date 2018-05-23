package gui.controllers;

public class InfoController {

    private MainController mainController;

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void back(){
        mainController.loadMenuScreen();
    }

}
