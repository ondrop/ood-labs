package com.company.memento;

import com.company.Application;
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
        System.out.println("save");
        Memento memento1 = originator.createMemento();
        ArrayList<ArrayList<JSONObject>> appState = Application.getInstance().getAppState();
        System.out.println(memento1.getState());
        System.out.println(appState);
        System.out.println(originator.getState());
        if (!originator.getState().toString().equals(appState.toString())) {
            System.out.println("Save 2");
            System.out.println(Application.getInstance().getShapes());
            System.out.println("Save 2 end");
            setMemento(memento1);
            originator.setState(appState);
        }

        if (memento != null) {
            System.out.println(memento.getState());
        }
        System.out.println(originator.getState());
    }

    public void undo() {
        System.out.println("undo");
        Memento oldMemento = originator.createMemento();
        originator.setMemento(getMemento());
        System.out.println(getMemento().getState());
        System.out.println(originator.getState());

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
