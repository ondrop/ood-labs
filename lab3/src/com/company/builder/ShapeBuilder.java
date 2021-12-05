package com.company.builder;

import com.company.shape.Shape;
import org.json.JSONObject;

import java.awt.*;

public interface ShapeBuilder {
    void setCoordinates(JSONObject coordinates);
    void setBackgroundColor(Color backgroundColor);
    void setBorderColor(Color borderColor);
    void setBorderWidth(int borderWidth);
    Shape getResult() throws Exception;
}
