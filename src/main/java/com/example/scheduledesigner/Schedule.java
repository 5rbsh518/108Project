package com.example.scheduledesigner;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {
    private ArrayList<Section> scheduleSections;

    Schedule(){

    }
    Schedule(ArrayList sections){
        this.scheduleSections = sections;
    }
    public ArrayList getScheduleSections() {
        return scheduleSections;
    }

    public void setScheduleSections(ArrayList scheduleSections) {
        this.scheduleSections = scheduleSections;
    }

    public void addSection(Section section){
        scheduleSections.add(section);
    }
    public void removeSection(Section section){
        scheduleSections.remove(section);
    }
}
