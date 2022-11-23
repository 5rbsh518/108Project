module com.example.scheduledesigner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scheduledesigner to javafx.fxml;
    exports com.example.scheduledesigner;
}