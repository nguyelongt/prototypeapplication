package login;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import database.DatabaseUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.ibatis.jdbc.SQL;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.IOException;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginScreenController {

    @FXML
    private AnchorPane loginScreenPane;


    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label messageLabel;

    public boolean isLogin() {
        boolean valid_credential = false;
        String username = usernameField.getText();
        String password = passwordField.getText();

        String sqlQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = DatabaseUtility.connection.prepareStatement(sqlQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
             if (rs.next()) {
                 if (rs.getString("username") != null && rs.getString("password") != null) {
                     valid_credential = true;
                 }
             }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("No error found");
        return valid_credential;
    }


    @FXML
    private void loginButtonClicked(ActionEvent event) throws IOException {
        if (isLogin()) {
            messageLabel.setText("");
            messageLabel.setText("Authentication Success!");
            messageLabel.setTextFill(Color.web("#1b43e5"));
        } else {
            usernameField.clear();
            passwordField.clear();
            System.out.println("Invalid username / password.");
            messageLabel.setText("Username / Password is incorrect. \nTry again.");
        }
    }

    @FXML
    private void registerButtonClicked(ActionEvent event) throws IOException {
        AnchorPane registerPane = FXMLLoader.load(getClass().getResource("/register/RegisterScreen.fxml"));
        loginScreenPane.getChildren().setAll(registerPane);
    }

}
