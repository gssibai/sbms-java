module com.example.sbms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.apache.commons.codec;
    requires totp;

    opens com.example.sbms to javafx.fxml;
    exports com.example.sbms;
}