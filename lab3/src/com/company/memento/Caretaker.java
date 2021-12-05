package com.company.memento;

import com.company.Application;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Caretaker {
    private Memento memento;
    private Originator originator;

    public Caretaker(Originator originator) {
        this.originator = originator;
    }

    public void save() {
        Memento oldState = originator.createMemento();
        JSONArray appState = Application.getInstance().getAppState();
        if (!originator.getState().toString().equals(appState.toString())) {
            setMemento(oldState);
            originator.setState(appState);
        }
    }

    public void undo() {
        originator.setMemento(getMemento());
        Application.getInstance().setHistoryState(Application.getInstance().getHistoryOverwrittenState());
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    public Originator getOriginator() {
        return originator;
    }
}
