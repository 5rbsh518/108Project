package com.example.javafx_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader editorLoader = new FXMLLoader(EditorController.class.getResource("TheTemplate.fxml"));
        Parent editor = editorLoader.load();
        FXMLLoader Temp2 = new FXMLLoader(EditorController.class.getResource("AnotherTemplate.fxml"));
        Parent Temp = Temp2.load();
        Scene scene = new Scene(editor);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}