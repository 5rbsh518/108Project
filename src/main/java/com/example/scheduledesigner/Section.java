package com.example.scheduledesigner;

import java.io.Serializable;

public class Section implements Serializable {
    private int CRN;
    private String activity;
    private String course;
    private  String sectionnumber;
    private String courseName;
    private String instructor;
    private String days;
    private int[] time;
    private String location;
    private boolean status;
    private boolean waitlist;

    Section(String course, String sectionnumber,String activity, int CRN, String courseName, String instructor, String days, int[] time,
            String location, boolean status, boolean waitlist){
        this.course =course;// Course number example "ICS104"
        this.sectionnumber = sectionnumber;
        this.activity = activity;
        this.CRN = CRN;
        this.courseName = courseName; // Course name example "Introduction to Programming in Python and C"
        this.instructor = instructor;
        this.days = days; // Each character is a day.
        this.time = time; //time[0] = the class start time, time[1] = the class end time.
        this.location = location;
        this.status = status;//True for open and false for closed
        this.waitlist = waitlist; //True for open and false for closed
    }
    //getters

    public String getActivity() {
        return activity;
    }
    public String getCourse() {
        return course;
    }

    public int getCRN() {
        return CRN;
    }

    public String getSectionnumber() {
        return sectionnumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDays() {
        return days;
    }

    public int[] getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isWaitlist() {
        return waitlist;
    }
    //setters

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSectionnumber(String sectionnumber) {
        this.sectionnumber = sectionnumber;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setTime(int[] time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setWaitlist(boolean waitlist) {
        this.waitlist = waitlist;
    }

    //Method

    @Override
    public String toString() {
        return this.getCourse() + "-" + this.getSectionnumber() + "," + this.getActivity() + "," + this.getCRN()
                + "," + this.getCourseName() + "," + this.getInstructor() + "," + this.getDays()+ "," +
                this.getTime()[0] +"-" + this.getTime()[1] + "," + this.getLocation() +"," + this.isStatus()
                + "," + this.isWaitlist();
    }
}
