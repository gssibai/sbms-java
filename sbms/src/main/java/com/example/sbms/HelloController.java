package com.example.sbms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button completebtn;

    @FXML
    private Button depositbtn;

    @FXML
    private Button homebtn;

    @FXML
    private Button infobtn;

    @FXML
    private Label nameTitle;

    @FXML
    private Button quitbtn;

    @FXML
    private Label surnameTitle;

    @FXML
    private Button withdrawbtn;
    @FXML
    private Pane paneDeposit;

    @FXML
    private Pane paneMain;

    @FXML
    private Pane paneWelcome;
    @FXML
    private Pane paneWithdraw;
    @FXML
    private Pane paneInfo;

    @FXML
    private Button btnDeleteAcc;
    @FXML
    private Pane paneName;

    private SecureAcc sa;

    // ********* Deposit Panel **********
    @FXML
    private Button btndepositDone;
    @FXML
    private TextField txtDAmount;

    // **************   Deposit Windows    *************


    @FXML
    void btndepositDone_Action(ActionEvent event) {
        if (!txtDAmount.equals(null)) {
            double amount = Double.parseDouble(txtDAmount.getText());
            sa.deposit(amount);
            message();
        } else {
            error();
            return;
        }
    }

    // *************     Withdraw Panel   **************
    @FXML
    private Label lblAccBalance;
    @FXML
    private TextField txtWamount;
    @FXML
    private Button btnWDone;

    @FXML
    void btnWithdraw_Action(ActionEvent event) {
        hidePanes();
        paneWithdraw.setVisible(true);
        withdrawbtn.getStyleClass().add("active");
        lblAccBalance.setText(sa.getBalance().toString());
    }

    @FXML
    void btnWDone_Action(ActionEvent event) {

        double amount = Double.parseDouble(txtWamount.getText());
        if (sa.withdraw(amount)) {
            lblAccBalance.setText(sa.getBalance().toString());
            message();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Error !\nYou don't enough balance");
            a.show();
        }

    }

// ****************

    void message() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setContentText("Your changes has been saved!");

    }

    //*************Personal informations panel buttons **********
    private void error() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setContentText("Error !\nDon't leave empty fileds");
        a.show();
    }

    @FXML
    private Label lblInfoName;
    @FXML
    private Label lblInfoSurname;

    @FXML
    private TextField txtReName;

    @FXML
    private TextField txtReSurname;
    @FXML
    private TextField txtRePhone;
    @FXML
    private Pane paneEditPhone;
    @FXML
    private Pane paneEditEmail;
    @FXML
    private Button btnEditEmail;
    @FXML
    private TextField txtEditEmail;

    @FXML
    private Button btnEditEmailDone;
    @FXML
    private Button btnEditPhoneDone;
    @FXML
    private Button btnEditNameDone;
    @FXML
    private Button btnDoneRePhone;
    @FXML
    private Label lblInfoPhone;
    @FXML
    private Label lblInfoId;
    @FXML
    private Label lblInfoEmail;


    @FXML
    private Pane paneMainPhone;
    @FXML
    private Pane paneMainName;
    @FXML
    private Pane paneMainEmail;


    @FXML
    void btnEditEmail_Action(ActionEvent event) {
        paneEditEmail.setVisible(true);
        paneMainEmail.setVisible(false);
    }

    @FXML
    void btnEditEmailDone_Action(ActionEvent event) {
        if (!txtEditEmail.getText().equals(null)) {
            sa.setEmail(txtEditEmail.getText());
            sa.save();
            message();
        } else {
            error();
            return;
        }
        paneMainEmail.setVisible(true);
        paneEditEmail.setVisible(false);
    }

    @FXML
    void btnEditPhone_Action(ActionEvent event) {
        paneEditPhone.setVisible(true);
        paneMainPhone.setVisible(false);

    }

    @FXML
    void btnDoneRePhone_Action(ActionEvent event) {
        if (txtRePhone.getText() != null) {
            sa.setPhoneNo(txtRePhone.getText());
            sa.save();
        } else {
            error();
            return;
        }
        paneMainPhone.setVisible(true);
        paneEditPhone.setVisible(false);
        message();
    }

    @FXML
    void btnDeleteAcc_Action(ActionEvent event) throws IOException {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Account deleting");
        a.setContentText("You are deleting your account\n\nAre you sure?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            sa.delete();
            Stage stage = (Stage) paneInfo.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }
    }

    @FXML
    void btnEditName_Action(ActionEvent event) {
        paneName.setVisible(true);
        paneMainName.setVisible(false);
    }

    @FXML
    void btnEditNameDone_Action(ActionEvent event) {
        if (txtReName.getText() != null) {
            sa.setName(txtReName.getText());
            sa.save();
        }
        if (txtReSurname.getText() != null) {
            sa.setSurname(txtReSurname.getText());
            sa.save();
        } else {
            error();
            return;
        }

        paneMainName.setVisible(true);

        paneName.setVisible(false);
    }

    //  ************
    @FXML
    void quit(MouseEvent event) {

        Stage stage = (Stage) quitbtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnDeposite_Action(ActionEvent event) {
        hidePanes();
        paneDeposit.setVisible(true);
        depositbtn.getStyleClass().add("active");
    }

    @FXML
    void btnHome_Action(ActionEvent event) {
        hidePanes();
        paneWelcome.setVisible(true);
        homebtn.getStyleClass().add("active");
    }


    @FXML
    void btnInfo_Action(ActionEvent event) {
        hidePanes();
        paneInfo.setVisible(true);
        infobtn.getStyleClass().add("active");
        setSa(sa);
        lblInfoName.setText((sa.getName()));
        lblInfoSurname.setText((sa.getSurname()));
        lblInfoId.setText(sa.getId());
        lblInfoEmail.setText(sa.getEmail());
        lblInfoPhone.setText(sa.getPhoneNo());
        lblAccBalance.setText(sa.getBalance().toString());
    }


    void hidePanes() {
        homebtn.getStyleClass().removeAll("active");
        depositbtn.getStyleClass().removeAll("active");
        withdrawbtn.getStyleClass().removeAll("active");
        infobtn.getStyleClass().removeAll("active");

        paneWelcome.setVisible(false);
        paneDeposit.setVisible(false);
        paneWithdraw.setVisible(false);
        paneInfo.setVisible(false);
        paneName.setVisible(false);
        paneEditPhone.setVisible(false);
        paneEditEmail.setVisible(false);

        txtDAmount.clear();
        txtWamount.clear();

    }

    public void setSa(SecureAcc sa) {
        this.sa = sa;
        nameTitle.setText(sa.getName());
        surnameTitle.setText(sa.getSurname());
        lblAccBalanceWelcome.setText(sa.getBalance().toString());

    }

    @FXML
    private Label lblAccBalanceWelcome;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        hidePanes();
        paneWelcome.setVisible(true);
        homebtn.getStyleClass().add("active");


    }
}