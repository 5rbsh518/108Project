package com.example.scheduledesigner;

public class Student {
    Course[] finishedCourses;

    Student(Course[] FinishedCourses){
        this.finishedCourses = finishedCourses;
    }

    public Course[] getFinishedCourses() {
        return finishedCourses;
    }

    public void setFinishedCourses(Course[] finishedCourses) {
        this.finishedCourses = finishedCourses;
    }
}
