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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
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
        var email = txtEmail.getText();
        var name = txtName.getText();
        var surname = txtSurname.getText();
        var phoneNo = txtPhoneNo.getText();

        var pass = txtPass.getText();
        var rePass = txtRePass.getText();
        Random id = new Random();
        int low = 1000, high = 9999;
        int idNo = id.nextInt(high - low) + low;

        if (pass.equals(rePass)) {
            try {
                File file = new File(idNo + ".txt");

                FileWriter wr = new FileWriter(file);
                String idString = Integer.toString(idNo);
                wr.write(email);
                wr.write(name);
                wr.write(surname);
                wr.write(phoneNo);
                wr.write(idString);
                wr.write(pass);
                wr.flush();
                wr.close();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Account ID");
                a.setContentText("Your Account has been created successfully \n\n" + "Your Account ID:  " + idNo);
                Optional<ButtonType> result = a.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Stage stage = (Stage) mainSignup.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                } else {
                    Alert b = new Alert(Alert.AlertType.INFORMATION);
                    b.setTitle("Cancelling");
                    b.setContentText("Account not created");
                    file.delete();
                    Stage stage = (Stage) mainSignup.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                }
                
            } catch (IOException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Error" + e);
                a.show();
            }
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Password not match");
            a.setContentText("re enter your password again");
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