package com.example.sbms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

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
    private TextField txtDAmount;
    @FXML
    private Button btnDeleteAcc;

    private SecureAcc sa;

    public void setSa(SecureAcc sa) {
        this.sa = sa;
    }

    @FXML
    void btnComplete_Action(ActionEvent event) {
      double amount=  Double.parseDouble(txtDAmount.getText());
        sa.deposit(amount);
    }

    @FXML
    void btnDeleteAcc_Action(ActionEvent event) {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Account deleting");
        a.setContentText("You are deleting your account\n\nAre you sure?");
        Optional<ButtonType> result= a.showAndWait();
        if (result.get() == ButtonType.OK){
            sa.delete();
        }
    }

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
    void btnWithdraw_Action(ActionEvent event) {
        hidePanes();
        paneWithdraw.setVisible(true);
        withdrawbtn.getStyleClass().add("active");
    }

    @FXML
    void btnInfo_Action(ActionEvent event) {
        hidePanes();
        paneInfo.setVisible(true);
        infobtn.getStyleClass().add("active");
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
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hidePanes();
        paneWelcome.setVisible(true);
        homebtn.getStyleClass().add("active");
        nameTitle.setText(sa.getName());
        surnameTitle.setText(sa.getSurname());
    }
}