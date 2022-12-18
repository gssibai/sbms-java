package com.example.sbms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button btnLogin;
    @FXML
    private Button quitbtn;

    @FXML
    private TextField txtId;

    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnCreatAcc;
    @FXML
    void btnQuit_Action(ActionEvent event) {
        Stage stage = (Stage) quitbtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnCreate_Action(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnCreatAcc.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    @FXML
    void btnLogin_click(MouseEvent event) throws IOException {
        var id = txtId.getText();
        var pass = txtPass.getText();

        if (id.equals("123") && pass.equals("123")) {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid username or password");
            a.show();
        }
    }

}
