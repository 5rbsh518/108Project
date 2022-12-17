package com.example.scheduledesigner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Basket {
    private ArrayList<Section> sections = new ArrayList<>();

    Basket() {

    }

        public ArrayList<Section> getSections() {
            return sections;
        }

        void addSection(Section section) {
            sections.add(section);
        }

        void removeSection(Section section) {
            sections.remove(section);
        }

        @Override
        public String toString() {
            return "Basket{" +
                    "sections=" + sections +
                    '}';
        }
    }

