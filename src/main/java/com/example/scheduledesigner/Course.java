package com.example.scheduledesigner;

import java.util.Arrays;

public class Course {
    private String courseName;
    private Course[] preCourses;
    private Course[] coreCourses;
    private int credit;
    Course(String name, Course[] preCourses, Course[] coreCourses, int credit){
        this.courseName = name;
        this.preCourses = preCourses;
        this.coreCourses = coreCourses;
        this.credit = credit;
    }
    //Getters
    public String getCourseName() {
        return courseName;
    }

    public Course[] getPreCourses() {
        return preCourses;
    }

    public Course[] getCoreCourses() {
        return coreCourses;
    }

    public int getCredit() {
        return credit;
    }
    //Setters

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setPreCourses(Course[] preCourses) {
        this.preCourses = preCourses;
    }

    public void setCoreCourses(Course[] coreCourses) {
        this.coreCourses = coreCourses;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
    //Methods


    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", preCourses=" + Arrays.toString(preCourses) +
                ", coreCourses=" + Arrays.toString(coreCourses) +
                ", credit=" + credit +
                '}';
    }
}
