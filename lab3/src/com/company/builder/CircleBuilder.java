package com.company.builder;

import com.company.shape.Circle;
import com.company.shape.Point;
import org.json.JSONObject;

import java.awt.*;

public class CircleBuilder implements ShapeBuilder {

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

    @Override
    public Circle getResult() throws Exception {
        Circle circle = new Circle();
        JSONObject center = this.coordinates.getJSONObject(Circle.CENTER_FIELD);
        com.company.shape.Point newCenter = new Point(center.getDouble("X"), center.getDouble("Y"));
        circle.setCenter(newCenter);
        circle.setRadius(coordinates.getDouble(Circle.RADIUS_FIELD));
        circle.setBackgroundColor(this.backgroundColor);
        circle.setBorderColor(this.borderColor);
        circle.setBorderWidth(this.borderWidth);

        return circle;
    }
}
