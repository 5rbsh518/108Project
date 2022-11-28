package com.example.scheduledesigner;

import java.util.Arrays;

public class Student {
    Course[] finishedCourses;

    Student(Course[] finishedCourses){
        this.finishedCourses = finishedCourses;
    }

    public Course[] getFinishedCourses() {
        return finishedCourses;
    }

    public void setFinishedCourses(Course[] finishedCourses) {
        this.finishedCourses = finishedCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "finishedCourses=" + Arrays.toString(finishedCourses) +
                '}';
    }
}
