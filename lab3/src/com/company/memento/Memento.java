package com.company.memento;

import org.json.JSONObject;

import java.util.ArrayList;

public class Memento {
    private ArrayList<ArrayList<JSONObject>> state;

    public Memento(ArrayList<ArrayList<JSONObject>> newState) {
        state = newState;
    }

    public ArrayList<ArrayList<JSONObject>> getState() {
        return state;
    }
}
