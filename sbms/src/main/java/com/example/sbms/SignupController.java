package com.example.sbms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.function.UnaryOperator;

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

    private void error(String errorType) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setContentText("Error !\nDon't leave " + errorType + " field empty");
        a.show();
    }

    @FXML
    void btnCreate_Action(ActionEvent event) throws IOException {


        var rePass = txtRePass.getText();
        if (!rePass.equals(txtPass.getText())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Re enter your password again");
            a.show();
            return;
        }

        SecureAcc sa = new SecureAcc();

            sa.setPass(txtPass.getText());


        if (!txtName.getText().equals(null)) {
            sa.setName(txtName.getText());
        } else {
            error("Name");
            return;
        }
        if (!txtSurname.getText().equals(null)) {
            sa.setSurname(txtSurname.getText());
        } else {
            error("Surname");
            return;
        }
              if (!txtEmail.getText().equals(null)){
                  sa.setEmail(txtEmail.getText());
              }else {
                  error("Email");
                  return;
              }

        if (!txtPhoneNo.getText().equals(null)){
        sa.setPhoneNo(txtPhoneNo.getText());}else {
            error("Phone number");
            return;
        }
        sa.save();

        if (sa.save()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Account ID");
            a.setContentText("Your Account has been created successfully \n\n" + "Your Account ID:  " + sa.getId());
            a.showAndWait();

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