package com.company.builder;

import com.company.shape.Point;
import com.company.shape.Triangle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;

public class TriangleBuilder implements ShapeBuilder {

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

    public Triangle getResult() throws Exception {
        Triangle triangle = new Triangle();

        JSONArray points = this.coordinates.getJSONArray(POINTS_FIELD);
        JSONObject vertex1 = (JSONObject) points.get(0);
        Point newVertex1 = new Point(vertex1.getDouble("X"), vertex1.getDouble("Y"));
        triangle.setVertex1(newVertex1);

        JSONObject vertex2 = (JSONObject) points.get(1);
        Point newVertex2 = new Point(vertex2.getDouble("X"), vertex2.getDouble("Y"));
        triangle.setVertex2(newVertex2);

        JSONObject vertex3 = (JSONObject) points.get(2);
        Point newVertex3 = new Point(vertex3.getDouble("X"), vertex3.getDouble("Y"));
        triangle.setVertex3(newVertex3);
        triangle.setBackgroundColor(this.backgroundColor);
        triangle.setBorderColor(this.borderColor);
        triangle.setBorderWidth(this.borderWidth);

        return triangle;
    }
}
