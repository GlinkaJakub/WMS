package gui.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;


public class EditWindowController {


    ObservableList list = FXCollections.observableArrayList();


    @FXML
    private ListView<String> editListView;
    static class XCell extends ListCell<String> {

        Button button = new Button("Del");
        Button button2 = new Button("Edit");
        HBox hbox = new HBox();
        Pane pane = new Pane();
        Label label = new Label("");

        public XCell( ) {
            super();

            hbox.getChildren().addAll(label, pane, button2, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button2.setOnMouseClicked(e -> {
                System.out.println("Tutaj wjebac okno");
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/fxml/EditListWindow.fxml"));
                AnchorPane anchorPane = null;
                try {
                    anchorPane = loader.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Scene scene = new Scene(anchorPane);
                Stage currentStage = new Stage();
                currentStage.setScene(scene);
                currentStage.setTitle("WMS");
                currentStage.show();

            });
            button.setOnAction(event -> getListView().getItems().remove(getItem()));
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                label.setText(item);
                setGraphic(hbox);
            }
        }
    }


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
        editListView.setCellFactory(param -> new XCell());

//        editListView.setCellFactory(param -> new XCell(button2));
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

}
