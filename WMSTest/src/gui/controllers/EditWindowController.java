package gui.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldListCell;


public class EditWindowController {



    ObservableList list = FXCollections.observableArrayList();


    @FXML
    private ListView<String> editListView;


    @FXML
    private Button editButton;

    private MainController mainController;

    @FXML
    private ToggleGroup toggleEdit;

    @FXML
    private TextField searchTextField;


    @FXML
    public void openProductList( ) {
    }

    @FXML
    public void openProductCardList( ) {
    }

    @FXML
    public void openContractorList( ) {
    }

    @FXML
    public void cancel(){
        mainController.loadMenuScreen();
    }

    @FXML
    public void doneButton( ) {
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOnAction( ) {
    }


    public void initialize(){
        loadData();

        editListView.setCellFactory(TextFieldListCell.forListView());
        editListView.setEditable(true);
//        editListView.getEditingIndex();
    }

    private void loadData() {
        list.removeAll(list);

        String a = "Dupa";
        String b = "Chuj";
        String c = "Cycki";

        list.addAll(a,b,c);
        editListView.getItems().addAll(list);

    }

    public void addObject(ActionEvent actionEvent) {
                editListView.getItems().add(0, "new");
    }

    public void deleteObject(ActionEvent actionEvent) {
       int  i = editListView.getEditingIndex();
        editListView.getItems().remove(i);
        i--;
    }
}
