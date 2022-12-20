package com.example.sbms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class SignupController {

    @FXML
    private Button backbtn;
    @FXML
    private Button createbtn;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private TextField txtSurname;
    @FXML
    private Pane mainSignup;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtRePass;


    @FXML
    void btnCreate_Action(ActionEvent event) throws IOException {


        var rePass = txtRePass.getText();
        if (!rePass.equals(txtPass.getText())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Password not match");
            a.setContentText("re enter your password again");
            a.show();
            return;
        }

        SecureAcc sa = new SecureAcc();
        sa.setPass(txtPass.getText());
        sa.setName(txtName.getText());
        sa.setSurname(txtSurname.getText());
        sa.setEmail(txtEmail.getText());
        sa.setPhoneNo(txtPhoneNo.getText());

        if (sa.save()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Account ID");
            a.setContentText("Your Account has been created successfully \n\n" + "Your Account ID:  " + sa.getId());
            a.show();

            Stage stage = (Stage) mainSignup.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Error occurred");
            a.show();
        }


    }

    @FXML
    void btnQuit_Clicked(MouseEvent event) {

    }

    @FXML
    void btnBack_Action(ActionEvent event) throws IOException {
        Stage stage = (Stage) backbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

}