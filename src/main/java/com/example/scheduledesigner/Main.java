package com.example.scheduledesigner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {
    private Basket basket = new Basket();

    public void start(Stage stage) throws IOException {
        //stuff
        Section[] courseOffering = FileManager.courseOffering2Sections("src/main/java/com/example/scheduledesigner/Data/CourseOffering.csv");
        Student student = FileManager.FinishedCourses2Student("src/main/java/com/example/scheduledesigner/Data/FinishedCourses.csv");
        Course[] degreePlan = FileManager.degreePlan2Courses("src/main/java/com/example/scheduledesigner/Data/DegreePlan.csv");
        Section[] Registrable = FileManager.registrableSections(courseOffering, student, degreePlan);


        //first Scene

        BorderPane borderPane = new BorderPane();

        borderPane.setPadding(new Insets(10,10,10,10));
        Label loadScheduleLabel = new Label("Add sections to Basket");
        loadScheduleLabel.setFont(new Font("Arial",30));
        loadScheduleLabel.setPadding(new Insets(5,250,5,5));


        //Load schedule button
        Button loadSchedule = new Button("Start with a saved schedule");
        loadSchedule.setPadding(new Insets(5,5,5,5));

        HBox top = new HBox();
        top.setPadding(new Insets(15,15,15,15));
        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(loadScheduleLabel,loadSchedule);
        borderPane.setTop(top);

        //Pane for registrable courses pane to add and remove
        ScrollPane courseOfferingPane = new ScrollPane();
        borderPane.setCenter(courseOfferingPane);
        courseOfferingPane.setPadding(new Insets(5,5,5,5));
        VBox vBox = new VBox();





        for (int i=0; i < Registrable.length;i++){
            Button btn1 = new Button();
            Button btn2 = new Button();
            Label index = new Label(Integer.toString(i));
            index.setVisible(false);
            btn1.setText("add");
            btn2.setText("remove");
            HBox hBox = new HBox();
            HBox btns = new HBox();
            btn2.setVisible(false);
            Registrable[i].setIndex(i);
            btn1.setOnAction(event -> {
                btn2.setVisible(true);
                btn1.setVisible(false);
                basket.addSection(Registrable[Integer.parseInt(index.getText())]);
                for(Section section: basket.getSections()){
                    System.out.println(section);
                }
                System.out.println("--------------------------------------");

            });
            btn2.setOnAction(event -> {
                btn1.setVisible(true);
                btn2.setVisible(false);
                basket.removeSection(Registrable[Integer.parseInt(index.getText())]);
                for(Section section: basket.getSections()){
                    System.out.println(section);
                }
                System.out.println("--------------------------------------");
            });
            hBox.setSpacing(500);
            btns.setSpacing(10);


            btns.getChildren().addAll(btn1,btn2);
            Label label = new Label(Registrable[i].toString());
            hBox.getChildren().addAll(label,btns);
            vBox.getChildren().add(hBox);
        }



        courseOfferingPane.setContent(vBox);


        //Next scene button
        Button next = new Button("Next");
        next.setPadding(new Insets(5,5,5,5));
        borderPane.setBottom(next);



        //Sceond scene
        BorderPane pane2 = new BorderPane();
        pane2.setPadding(new Insets(10,10,10,10));
        VBox sectionBasket = new VBox();

        pane2.setRight(sectionBasket);












        Scene scene1 = new Scene(borderPane);
        Scene scene2 = new Scene(pane2);
        stage.setHeight(1000);
        stage.setWidth(1500);
        stage.setScene(scene1);

        next.setOnAction(event -> {
            stage.setScene(scene2);
            Schedule currentShcedule = new Schedule();
            for (int i =0; i<basket.getSections().size();i++){
                Label index = new Label(Integer.toString(i));
                HBox sectionSlot = new HBox();
                sectionSlot.setPadding(new Insets(10,10,10,10));
                sectionSlot.setSpacing(10);
                Button addToSh = new Button("add");
                addToSh.setOnAction(e -> {
                    currentShcedule.addSection(basket.getSections().get(Integer.parseInt(index.getText())));
                    for(Section section: currentShcedule.getScheduleSections()){
                        System.out.println(section);
                    }

                });
                Label sectionLabel = new Label(basket.getSections().get(i).getCourse()+"-"+basket.getSections().get(i).getSectionnumber()+"\n"+basket.getSections().get(i).getTime()[0]+"-"+basket.getSections().get(i).getTime()[1]);
                sectionSlot.getChildren().addAll(sectionLabel,addToSh);
                sectionBasket.getChildren().add(sectionSlot);

            }
        });

        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }



}