package com.company.shape;

import com.company.builder.ShapeBuilder;
import org.json.JSONObject;

import java.awt.*;
import java.util.HashMap;

public interface Shape {
    String TYPE_FIELD = "type";
    String COORDINATES_FIELD = "coordinates";
    String BACKGROUND_COLOR_FIELD  = "backgroundColor";
    String BORDER_COLOR_FIELD  = "borderColor";
    String BORDER_WIDTH_FIELD  = "borderWidth";
    String CHILDREN_FIELD = "children";

    void draw(Graphics graphics);

    double getArea();

    double getPerimeter();

    String getGid();

    double getWidth();

    double getHeight();

    Point calcLeftTopPoint();

    boolean isInsideBounds(double x, double y);

    void translate(int x, int y);

    void setBackgroundColor(Color color);

    Color getBackgroundColor();

    void setBorderColor(Color borderColor);

    Color getBorderColor();

    int getBorderWidth();

    void setBorderWidth(int borderWidth);

    JSONObject getState();

    JSONObject getCoordinates();

    ShapeBuilder getBuilder();
}
