package com.example.scheduledesigner;

import java.io.File;
import java.util.Scanner;

public class FileManager {
    static Section[] courseOffering2Sections(String fileLocation){ // Done and Working
        Section[] outputSections;
        int CRN;
        String activity;
        String course;
        String sectionnumber;
        String courseName;
        String instructor;
        String days;
        String location;
        boolean status;
        boolean waitlist;
        int fileLines=0;
        String[] data;
        File file = new File(fileLocation);
        try {
            Scanner input = new Scanner(file);
            input.nextLine();// This to remove the first line (Information line)
            while (input.hasNextLine()){// This will loop to count how many element to initialize the array
                input.nextLine();
                fileLines++;
            }
            input.close();
            outputSections = new Section[fileLines];
            fileLines = 0;
            input = new Scanner(file);
            input.nextLine();// This to remove the first line (Information line)
            while (input.hasNextLine()){
                int[] time = new int[2];
                data = input.nextLine().split(",");
                course = data[0].split("-")[0];
                sectionnumber = data[0].split("-")[1];
                activity = data[1];
                CRN = Integer.parseInt(data[2]);
                courseName = data[3];
                instructor = data[4];
                days = data[5];
                if(data[6].equals("")){// Sometimes time is empty so it will replace it with 0000-0000
                    time[0] = 0;
                    time[1] = 0;
                }else{
                    time[0] = Integer.parseInt(data[6].split("-")[0]);
                    time[1] = Integer.parseInt(data[6].split("-")[1]);
                }
                location = data[7];
                status = data[8].equals("Open");
                waitlist = data[9].equals("Open");
                outputSections[fileLines++] = new Section(course, sectionnumber, activity, CRN, courseName,instructor,days,time, location, status, waitlist);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            outputSections = null;// If outputSections is null then an error occurred
        }
        return outputSections;
    }

    static Student FinishedCourses2Student(String fileLocation){
        Student outputStudent;
        File file = new File(fileLocation);
        try{
            String[] data;
            Course[] finishedCourses;
            int counter = 0;
            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                ++counter;
                input.nextLine();
            }
            input.close();
            input = new Scanner(file);
            finishedCourses = new Course[counter];
            counter = 0;
            while (input.hasNextLine()){
                data = input.nextLine().split(",");
                finishedCourses[counter++] = new Course(data[0],null,null,0);
            }
            outputStudent = new Student(finishedCourses);
        }catch (Exception e){
            System.out.println(e.getMessage());
            outputStudent = null;
        }

        return outputStudent;
    }
    static Course[] degreePlan2Courses(String fileLocation){
        Course[] PlanCourses;
        File file = new File(fileLocation);
        try{
            String[] data;
            int counter = 0;
            Scanner input = new Scanner(file);
            input.nextLine();
            while (input.hasNextLine()){
                ++counter;
                input.nextLine();
            }
            input.close();
            input = new Scanner(file);
            PlanCourses = new Course[counter];
            counter = 0;
            input.nextLine();
            while (input.hasNextLine()) {
                Course[] preCourses;
                Course[] coreCourses;
                data = input.nextLine().split(",",-1);
                if(data[2].equals("None") || data[2].equals("")){
                    preCourses = null;
                }else{
                    preCourses = new Course[data[2].split(";").length];
                    int listCounter = 0;
                    for(String courseName: data[2].split(";")){
                        preCourses[listCounter++] = new Course(courseName,null,null,0);
                    }
                }
                if(data[3].equals("None") || data[3].equals("")){
                    coreCourses = null;
                }else{
                    coreCourses = new Course[data[3].split(";").length];
                    int listCounter = 0;
                    for(String courseName: data[3].split(";")){
                        coreCourses[listCounter++] = new Course(courseName,null,null,0);
                    }
                }
                    int credit = Integer.parseInt(data[1]);
                PlanCourses[counter++] = new Course(data[0],preCourses,coreCourses,credit);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            PlanCourses = null;
        }
        return PlanCourses;
    }
}