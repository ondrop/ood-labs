package com.company.memento;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Originator {
    private JSONArray state = new JSONArray();

    public Originator() {}

    public Memento createMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento memento) {
        state = memento.getState();
    }

    public void setState(JSONArray state) {
        this.state = state;
    }

    public JSONArray getState() {
        return state;
    }
}
