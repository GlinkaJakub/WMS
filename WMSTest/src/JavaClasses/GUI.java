package JavaClasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/gui/fxml/StartWindow.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("WMS");
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}
