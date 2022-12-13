package com.example.scheduledesigner;

public class Main {
    public static void main(String[] args) {
        Student s = FileManager.FinishedCourses2Student("C:\\Users\\omerr\\IdeaProjects\\108Project\\src\\main\\java\\com\\example\\scheduledesigner\\Data\\FinishedCourses.csv");
        Course[] degreePlan = FileManager.degreePlan2Courses("C:\\Users\\omerr\\IdeaProjects\\108Project\\src\\main\\java\\com\\example\\scheduledesigner\\Data\\DegreePlan.csv");
        Section[] CourseOffering = FileManager.courseOffering2Sections("C:\\Users\\omerr\\IdeaProjects\\108Project\\src\\main\\java\\com\\example\\scheduledesigner\\Data\\CourseOffering.csv");
        Section[] f = FileManager.registrableSections(CourseOffering, s, degreePlan);
        for(Section se: f){
            System.out.println(se);
        }
    }
}