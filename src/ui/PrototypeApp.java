package ui;

import database.DatabaseUtility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrototypeApp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login/LoginScreen.fxml"));
            primaryStage.setTitle("Prototype Application");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DatabaseUtility.getConnection();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
