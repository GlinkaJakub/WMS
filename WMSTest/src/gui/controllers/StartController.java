package gui.controllers;

import JavaClasses.Facade;
import JavaClasses.Management;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class StartController {

    @FXML
    private TextField databaseField;

    @FXML
    private TextField hostField;

    @FXML
    private TextField portField;

    @FXML
    private TextField userField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button connectButton;

    @FXML
    private Label alert;

    @FXML
    void initialize(){

    }

    public void connectToTheServer() throws IOException {
        String com = Facade.ConnectToTheServer(databaseField.getText(),hostField.getText(),portField.getText(),userField.getText(),passwordField.getText());

        /*if(com.equals("Success")) {

            Stage previousStage = (Stage) alert.getScene().getWindow();
            previousStage.close();

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gui/fxml/MainWindow.fxml"));
            StackPane stackPane = loader.load();
            Scene scene = new Scene(stackPane, 900, 600);
            Stage currentStage = new Stage();
            currentStage.setScene(scene);
            currentStage.setTitle("WMS");
            currentStage.show();
        }else {
            alert.setTextFill(Color.RED);
            alert.setText(com);
        }*/
   }

	public void selectPersons(){
        alert.setText(Facade.selectPersons());
    }

}
