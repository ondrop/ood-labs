package com.company.state;

import com.company.Application;
import com.company.shape.ShapeCompound;

import java.util.ArrayList;

public class HistoryOverwrittenState implements HistoryState {

    @Override
    public ArrayList<ShapeCompound> getShapes() {
        Application app = Application.getInstance();
        Application.getInstance().setHistoryState(app.getHistoryNotOverwrittenState());
        ArrayList<ShapeCompound> newShapes = Application.parseStateShapes(app.getCaretaker().getOriginator().getState());
        Application.getInstance().setShapes(newShapes);
        return newShapes;
    }
}
