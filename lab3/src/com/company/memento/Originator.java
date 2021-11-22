package com.company.memento;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Originator {
    private ArrayList<JSONObject> state = new ArrayList<>();

    public Originator() {}

    public Memento createMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento memento) {
        state = memento.getState();
    }

    public void setState(ArrayList<JSONObject> state) {
        this.state = state;
    }

    public ArrayList<JSONObject> getState() {
        return state;
    }
}
