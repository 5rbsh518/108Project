package com.example.scheduledesigner;

import java.io.Serializable;

public class Schedule implements Serializable {
    private Section[] scheduleSections;

    Schedule(Section[] sections){
        this.scheduleSections = sections;
    }

    public Section[] getScheduleSections() {
        return scheduleSections;
    }

    public void setScheduleSections(Section[] scheduleSections) {
        this.scheduleSections = scheduleSections;
    }
}
