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

import java.io.File;
import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {
    private Basket basket = new Basket();
    private Schedule currentShcedule = new Schedule();

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

        ScrollPane sectionBasketMain = new ScrollPane();
        VBox sectionBasket = new VBox();
        sectionBasketMain.setContent(sectionBasket);
        sectionBasketMain.setPadding(new Insets(5,5,5,5));
        sectionBasketMain.setMinWidth(100);


        ScrollPane sectionScheduleMain = new ScrollPane();
        VBox sectionSchedule = new VBox();
        sectionScheduleMain.setContent(sectionSchedule);
        sectionScheduleMain.setPadding(new Insets(5,5,5,5));
        sectionScheduleMain.setMinWidth(100);

        GridPane schedule = new GridPane();
        Label label = new Label(" ");
        Label label0 = new Label(" ");
        Label label1 = new Label("SUN");
        Label label2= new Label("MON");
        Label label3 = new Label("TUE");
        Label label4 = new Label("WED");
        Label label5 = new Label("THU");
        Label Time0 = new Label("07:00");
        Label Time1 = new Label("08:00");
        Label Time2= new Label("10:00");
        Label Time3 = new Label("11:00");
        Label Time4 = new Label("12:00");
        Label Time5 = new Label("13:00");
        Label Time6 = new Label("14:00");
        Label Time7 = new Label("15:00");
        Label Time8= new Label("16:00");
        Label Time9 = new Label("17:00");
        Button save = new Button("Save Schedule");
        schedule.add(label, 0, 1, 1, 1);
        schedule.add(label0, 1, 1, 1, 1);
        schedule.add(label1, 2, 1, 1, 1);
        schedule.add(label2, 3, 1, 1, 1);
        schedule.add(label3, 4, 1, 1, 1);
        schedule.add(label4, 5, 1, 1, 1);
        schedule.add(label5, 6, 1, 1, 1);
        schedule.add(Time0, 1, 2, 1, 1);
        schedule.add(Time1, 1, 3, 1, 1);
        schedule.add(Time2, 1, 4, 1, 1);
        schedule.add(Time3, 1, 5, 1, 1);
        schedule.add(Time4, 1, 6, 1, 1);
        schedule.add(Time5, 1, 7, 1, 1);
        schedule.add(Time6, 1, 8, 1, 1);
        schedule.add(Time7, 1, 9, 1, 1);
        schedule.add(Time8, 1, 10, 1, 1);
        schedule.add(Time9, 1, 11, 1, 1);



        save.setOnAction(event -> {
            FileManager.saveSchedule(currentShcedule,"saved");
        });







        schedule.setHgap(80);
        schedule.setVgap(35);




        pane2.setCenter(schedule);
        pane2.setRight(sectionBasketMain);
        pane2.setBottom(save);
        pane2.setLeft(sectionScheduleMain);












        Scene scene1 = new Scene(borderPane);
        Scene scene2 = new Scene(pane2);
        stage.setHeight(1000);
        stage.setWidth(1500);
        stage.setScene(scene1);

        loadSchedule.setOnAction(event -> {
            currentShcedule = FileManager.loadSchedule("saved");
            basket = new Basket(currentShcedule.getScheduleSections());
            stage.setScene(scene2);
            stage.setTitle("Schedule Builder");

            for (int i =0; i<basket.getSections().size();i++){
                Label index = new Label(Integer.toString(i));
                HBox sectionSlot = new HBox();
                sectionSlot.setPadding(new Insets(10,10,10,10));
                sectionSlot.setSpacing(10);
                Button addToSh = new Button("del");
                Label sectionLabel = new Label(basket.getSections().get(i).getCourse()+"-"+basket.getSections().get(i).getSectionnumber()+
                        "\n"+basket.getSections().get(i).getTime()[0]+"-"+basket.getSections().get(i).getTime()[1]+" "+
                        "-"+basket.getSections().get(i).getDays());
                addToSh.setOnAction(e -> {
                    if(currentShcedule.addable(basket.getSections().get(Integer.parseInt(index.getText())))){
                        currentShcedule.addSection(basket.getSections().get(Integer.parseInt(index.getText())));
                        addToSh.setVisible(false);
                        HBox scheduleSlot = new HBox();
                        scheduleSlot.setPadding(new Insets(10,10,10,10));
                        scheduleSlot.setSpacing(10);
                        Button removeToSh = new Button("del");
                        scheduleSlot.getChildren().addAll(sectionLabel,removeToSh);
                        sectionSchedule.getChildren().add(scheduleSlot);
                    }

                });
                sectionSlot.getChildren().addAll(sectionLabel,addToSh);
                sectionSchedule.getChildren().add(sectionSlot);

            }
        });


        next.setOnAction(event -> {
            stage.setScene(scene2);
            stage.setTitle("Schedule Builder");

            for (int i =0; i<basket.getSections().size();i++){
                Label index = new Label(Integer.toString(i));
                HBox sectionSlot = new HBox();
                sectionSlot.setPadding(new Insets(10,10,10,10));
                sectionSlot.setSpacing(10);
                Button addToSh = new Button("add");
                Label sectionLabel = new Label(basket.getSections().get(i).getCourse()+"-"+basket.getSections().get(i).getSectionnumber()+
                        "\n"+basket.getSections().get(i).getTime()[0]+"-"+basket.getSections().get(i).getTime()[1]+" "+
                        "-"+basket.getSections().get(i).getDays());
                addToSh.setOnAction(e -> {
                    if(currentShcedule.addable(basket.getSections().get(Integer.parseInt(index.getText())))){
                        currentShcedule.addSection(basket.getSections().get(Integer.parseInt(index.getText())));
                        sectionSlot.setVisible(false);
                        HBox scheduleSlot = new HBox();
                        scheduleSlot.setPadding(new Insets(10,10,10,10));
                        scheduleSlot.setSpacing(10);
                        Button removeToSh = new Button("del");
                        removeToSh.setOnAction(event1 -> {
                            sectionSlot.setVisible(true);
                            scheduleSlot.setVisible(false);
                        });
                        scheduleSlot.getChildren().addAll(sectionLabel,removeToSh);
                        sectionSchedule.getChildren().add(scheduleSlot);
                    }

                });

                sectionSlot.getChildren().addAll(sectionLabel,addToSh);
                sectionBasket.getChildren().add(sectionSlot);

            }
        });
        stage.setTitle("CourseOffering");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }



}