package com.company.state;

import com.company.Application;
import com.company.shape.ShapeCompound;

import java.util.ArrayList;

public class HistoryNotOverwrittenState implements HistoryState {

    @Override
    public ArrayList<ShapeCompound> getShapes() {
        return Application.getInstance().getShapes();
    }
}
