package com.example.scheduledesigner;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {
    private ArrayList<Section> scheduleSections = new ArrayList<>();

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
    public boolean addable(Section section){
        return addableCourse(section) && addableTime(section);
    }

    private boolean addableCourse(Section section){
        for(Section scheduleSection: scheduleSections){
            if(scheduleSection.getCourse().equals(section.getCourse())){
                return false;
            }
        }
        return true;
    }
    private boolean addableTime(Section section){
        if(section.getDays().equals("None") || (section.getTime()[0] == 0 &&section.getTime()[1] == 0 )){
            return true;
        }
        for(Character day: section.getDays().toCharArray()){
            for(Section scheduleSection: scheduleSections){
                if(scheduleSection.getDays().contains(day.toString())){//If this section conflict in day with another section check if there is also a time confliction.
                    if((scheduleSection.getTime()[0] <= section.getTime()[0] && section.getTime()[0] <= scheduleSection.getTime()[1]) || (scheduleSection.getTime()[0] <= section.getTime()[1] && section.getTime()[1] <= scheduleSection.getTime()[1])){
                        return false;//There is a conflict
                    }
                }

            }
        }
        return true;
    }
}
