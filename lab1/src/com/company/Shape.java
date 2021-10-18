package com.company;

import java.awt.*;

public interface Shape {
    void draw(Graphics graphics);

    double getArea();

    double getPerimeter();

    String getGid();

    double getWidth();

    double getHeight();

    Point calcLeftTopPoint();
}
