package com.company.builder;

import com.company.shape.Rectangle;
import com.company.shape.Point;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;

public class RectangleBuilder implements ShapeBuilder {

    public static final String POINTS_FIELD = "points";

    private JSONObject coordinates;
    private Color backgroundColor;
    private Color borderColor;
    private int borderWidth;

    @Override
    public void setCoordinates(JSONObject coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public Rectangle getResult() throws Exception {
        Rectangle rectangle = new Rectangle();
        JSONArray points = coordinates.getJSONArray(POINTS_FIELD);
        JSONObject leftTop = (JSONObject) points.get(0);
        Point newLeftTop = new Point(leftTop.getDouble("X"), leftTop.getDouble("Y"));
        rectangle.setLeftTop(newLeftTop);

        JSONObject rightBottom = (JSONObject) points.get(1);
        Point newRightBottom = new Point(rightBottom.getDouble("X"), rightBottom.getDouble("Y"));
        rectangle.setRightBottom(newRightBottom);
        rectangle.setBackgroundColor(this.backgroundColor);
        rectangle.setBorderColor(this.borderColor);
        rectangle.setBorderWidth(this.borderWidth);

        return rectangle;
    }
}
