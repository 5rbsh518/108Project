package com.example.scheduledesigner;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    static Section[] courseOffering2Sections(String fileLocation) { // Done and Working
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
        int fileLines = 0;
        String[] data;
        File file = new File(fileLocation);
        try {
            Scanner input = new Scanner(file);
            input.nextLine();// This to remove the first line (Information line)
            while (input.hasNextLine()) {// This will loop to count how many element to initialize the array
                input.nextLine();
                fileLines++;
            }
            input.close();
            outputSections = new Section[fileLines];
            fileLines = 0;
            input = new Scanner(file);
            input.nextLine();// This to remove the first line (Information line)
            while (input.hasNextLine()) {
                int[] time = new int[2];
                data = input.nextLine().split(",",-1);
                course = data[0].split("-")[0];
                sectionnumber = data[0].split("-")[1];
                activity = data[1];
                CRN = Integer.parseInt(data[2]);
                courseName = data[3];
                instructor = data[4];
                days = data[5];
                if (data[6].equals("") || data[6].equals("None")) {// Sometimes time is empty so it will replace it with 0000-0000
                    time[0] = 0;
                    time[1] = 0;
                } else {
                    time[0] = Integer.parseInt(data[6].split("-")[0]);
                    time[1] = Integer.parseInt(data[6].split("-")[1]);
                }
                location = data[7];
                status = data[8].equals("Open");
                waitlist = data[9].equals("Open");
                outputSections[fileLines++] = new Section(course, sectionnumber, activity, CRN, courseName, instructor, days, time, location, status, waitlist);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            outputSections = null;// If outputSections is null then an error occurred
        }
        return outputSections;
    }

    static Student FinishedCourses2Student(String fileLocation) {
        Student outputStudent;
        File file = new File(fileLocation);
        try {
            String[] data;
            Course[] finishedCourses;
            int counter = 0;
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                ++counter;
                input.nextLine();
            }
            input.close();
            input = new Scanner(file);
            finishedCourses = new Course[counter];
            counter = 0;
            while (input.hasNextLine()) {
                data = input.nextLine().split(",");
                finishedCourses[counter++] = new Course(data[0], null, null, 0);
            }
            outputStudent = new Student(finishedCourses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            outputStudent = null;
        }

        return outputStudent;
    }

    static Course[] degreePlan2Courses(String fileLocation) {
        Course[] PlanCourses;
        File file = new File(fileLocation);
        try {
            String[] data;
            int counter = 0;
            Scanner input = new Scanner(file);
            input.nextLine();
            while (input.hasNextLine()) {
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
                data = input.nextLine().split(",", -1);
                if (data[2].equals("None") || data[2].equals("")) {
                    preCourses = null;
                } else {
                    preCourses = new Course[data[2].split(";").length];
                    int listCounter = 0;
                    for (String courseName : data[2].split(";")) {
                        preCourses[listCounter++] = new Course(courseName, null, null, 0);
                    }
                }
                if (data[3].equals("None") || data[3].equals("")) {
                    coreCourses = null;
                } else {
                    coreCourses = new Course[data[3].split(";").length];
                    int listCounter = 0;
                    for (String courseName : data[3].split(";")) {
                        coreCourses[listCounter++] = new Course(courseName, null, null, 0);
                    }
                }
                int credit = Integer.parseInt(data[1]);
                PlanCourses[counter++] = new Course(data[0], preCourses, coreCourses, credit);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            PlanCourses = null;
        }
        return PlanCourses;
    }

    public static Section[] registrableSections(Section[] courseOffering, Student student, Course[] degreePlan) {
        Course[] finishedCourse = student.getFinishedCourses();
        ArrayList<String> registrableCoursesNames = new ArrayList<>();
        ArrayList<Section> registrableSections = new ArrayList<>();
        for (Course studentCourse : degreePlan) {
            boolean courseFlag = true;
            Course[] preCourses = studentCourse.getPreCourses();
            if (preCourses != null) {
                for (int i = 0; i < preCourses.length && courseFlag; ++i) {
                    boolean preCourseFlag = false;
                    for (int j = 0; j < student.getFinishedCourses().length && !preCourseFlag; ++j) {
                        if (preCourses[i].equals(student.getFinishedCourses()[j])) {
                            preCourseFlag = true;
                        }
                    }
                    if (!preCourseFlag) {
                        courseFlag = false;
                    }
                }
            }
            if (courseFlag) {
                registrableCoursesNames.add(studentCourse.getCourseName());
            }
        }
        for (int i = 0; i < student.getFinishedCourses().length; ++i) {
            if (registrableCoursesNames.contains(student.getFinishedCourses()[i].getCourseName())) {
                registrableCoursesNames.remove(student.getFinishedCourses()[i].getCourseName());
            }
        }
        for (Section section : courseOffering) {
            if (registrableCoursesNames.contains(section.getCourse())) {
                registrableSections.add(section);
            }
        }
        Section[] output = registrableSections.toArray(new Section[0]);
        return output;
    }

    public static void saveSchedule(Schedule schedule, String name) {
        try (FileOutputStream outputFile = new FileOutputStream("src/main/java/com/example/scheduledesigner/Data/Schedules/" + name + ".dat");
             ObjectOutputStream output = new ObjectOutputStream(outputFile);) {
            output.writeObject(schedule);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Schedule loadSchedule(String name) {
        Schedule output;
        try (FileInputStream inputFile = new FileInputStream("src/main/java/com/example/scheduledesigner/Data/Schedules/" + name + ".dat");
             ObjectInputStream input = new ObjectInputStream(inputFile);) {
            output = (Schedule) input.readObject();
            return output;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;//No file found
    }
}

