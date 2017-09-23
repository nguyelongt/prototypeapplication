package register;

import database.DatabaseUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterScreenController {

    @FXML
    private AnchorPane registerScreenPane;

    @FXML
    private TextField usernameRegField;

    @FXML
    private TextField passwordRegField;

    @FXML
    private Label messageLabel;

    @FXML
    private void loginscreenButtonClicked(ActionEvent event) throws IOException {
        AnchorPane loginPane = FXMLLoader.load(getClass().getResource("/login/LoginScreen.fxml"));
        registerScreenPane.getChildren().setAll(loginPane);
    }

    @FXML
    private void registerButtonClicked(ActionEvent event) throws IOException {
        if (createUser()) {
            System.out.println("New user registered.");
            messageLabel.setText("");
            AnchorPane loginPane = FXMLLoader.load(getClass().getResource("/login/LoginScreen.fxml"));
            registerScreenPane.getChildren().setAll(loginPane);
        } else {
            usernameRegField.clear();
            passwordRegField.clear();
            messageLabel.setText("Username already exists. \nTry again.");
        }

    }

    @SuppressWarnings("Duplicates")
    public boolean createUser() {
        boolean create_user = false;
        String username = usernameRegField.getText();
        String password = passwordRegField.getText();

        String sqlQuery = "INSERT INTO users (username, password) VALUES (? , ?)";
        try {
            PreparedStatement ps = DatabaseUtility.connection.prepareStatement(sqlQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            create_user = true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("No error found");
        return create_user;
    }
}
