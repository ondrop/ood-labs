package com.company.memento;

import org.json.JSONObject;

import java.util.ArrayList;

public class Memento {
    private ArrayList<JSONObject> state;

    public Memento(ArrayList<JSONObject> newState) {
        state = newState;
    }

    public ArrayList<JSONObject> getState() {
        return state;
    }
}
