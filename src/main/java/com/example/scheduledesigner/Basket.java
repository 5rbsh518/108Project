package com.example.scheduledesigner;

import java.util.Arrays;

public class Basket {
    private Section[] sections;

    Basket(Section[] sections){
        this.sections = sections;
    }

    public Section[] getSections() {
        return sections;
    }

    void addSection(Section section){
        Section[] sections1 = new Section[sections.length + 1];
        for(int i = 0; i < this.sections.length; ++i){
            sections1[i] = this.sections[i];
        }
        sections1[sections1.length] = section;
    }

    void removeSection(int index){
        if( 0 < index || index < this.sections.length) {
            Section[] sections1 = new Section[sections.length - 1];
            int counter = 0;
            for (int i = 0; i < this.sections.length; ++i) {
                if (i == index) {
                }//skip
                else {
                    sections1[counter++] = sections1[i];
                }
            }
        }
        else {
        }

    }

    @Override
    public String toString() {
        return "Basket{" +
                "sections=" + Arrays.toString(sections) +
                '}';
    }
}
