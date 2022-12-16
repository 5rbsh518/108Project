package com.example.scheduledesigner;

public class Main {
    public static void main(String[] args) {
        Section[] courseOffering = FileManager.courseOffering2Sections("src/main/java/com/example/scheduledesigner/Data/CourseOffering.csv");
        Student student = FileManager.FinishedCourses2Student("src/main/java/com/example/scheduledesigner/Data/FinishedCourses.csv");
        Course[] degreePlan = FileManager.degreePlan2Courses("src/main/java/com/example/scheduledesigner/Data/DegreePlan.csv");
        Section[] registrableSections = FileManager.registrableSections(courseOffering, student, degreePlan);
            for(Section e: registrableSections){
                System.out.println(e);
            }
        }
    }
