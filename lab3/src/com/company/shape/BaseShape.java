package com.company.shape;

import java.awt.*;

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
}
