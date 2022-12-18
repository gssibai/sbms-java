module com.example.sbms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.sbms to javafx.fxml;
    exports com.example.sbms;
}