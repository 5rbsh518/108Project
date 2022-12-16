module com.example.javafx_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.javafx_project to javafx.fxml;
    exports com.example.javafx_project;
    opens com.example.javafx_project.Main to javafx.fxml;
}