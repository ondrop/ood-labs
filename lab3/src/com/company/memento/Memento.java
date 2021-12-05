package com.company.memento;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Memento {
    private JSONArray state;

    public Memento(JSONArray newState) {
        state = newState;
    }

    public JSONArray getState() {
        return state;
    }
}
