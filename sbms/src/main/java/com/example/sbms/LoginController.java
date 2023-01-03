package com.example.sbms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private TextField txtTwoFA;

    @FXML
    private TextField txtIdLogin;

    @FXML
    private PasswordField txtPassLogin;
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

        SecureAcc sa = SecureAcc.getSA(txtIdLogin.getText(), txtPassLogin.getText());


        if (sa == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Invalid username or password");
            a.show();
        } else if (!sa.is2faValid(txtTwoFA.getText())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Invalid 2FA code");
            a.show();
        } else {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
           Parent root =(Parent) fxmlLoader.load();
            HelloController controller = fxmlLoader.<HelloController>getController();
            controller.setSa(sa);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

}
