package com.company.state;

import com.company.shape.ShapeCompound;

import java.util.ArrayList;

public interface HistoryState {
    ArrayList<ShapeCompound> getShapes();
}
