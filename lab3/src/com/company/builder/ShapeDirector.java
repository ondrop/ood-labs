package com.company.builder;

import org.json.JSONObject;

import java.awt.*;

public class ShapeDirector {
    private static final String COORDINATES_FIELD = "coordinates";
    private static final String BACKGROUND_COLOR_FIELD  = "backgroundColor";
    private static final String BORDER_COLOR_FIELD  = "borderColor";
    private static final String BORDER_WIDTH_FIELD  = "borderWidth";

    public static void createShape(ShapeBuilder builder, JSONObject shapeData) {
        builder.setCoordinates(shapeData.getJSONObject(COORDINATES_FIELD));
        builder.setBackgroundColor(new Color(shapeData.getInt(BACKGROUND_COLOR_FIELD)));
        builder.setBorderColor(new Color(shapeData.getInt(BORDER_COLOR_FIELD)));
        builder.setBorderWidth(shapeData.getInt(BORDER_WIDTH_FIELD));
    }
}
