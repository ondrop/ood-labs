package com.company.shape;

import org.json.JSONObject;

import java.awt.*;
import java.util.HashMap;

abstract class BaseShape implements Shape {
    protected Color backgroundColor = Color.WHITE;
    protected Color borderColor = Color.BLACK;
    protected int borderWidth = 1;

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public JSONObject getState() {
        JSONObject state = new JSONObject();
        state.put(TYPE_FIELD, getGid());
        state.put(COORDINATES_FIELD, getCoordinates());
        state.put(BACKGROUND_COLOR_FIELD, getBackgroundColor().getRGB());
        state.put(BORDER_COLOR_FIELD, getBorderColor().getRGB());
        state.put(BORDER_WIDTH_FIELD, getBorderWidth());

        return state;
    }

    @Override
    public void setData(JSONObject shapeData) {
        setBackgroundColor(new Color(shapeData.getInt(BACKGROUND_COLOR_FIELD)));
        setBorderColor(new Color(shapeData.getInt(BORDER_COLOR_FIELD)));
        setBorderWidth(shapeData.getInt(BORDER_WIDTH_FIELD));
    }
}
