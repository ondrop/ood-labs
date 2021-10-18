package com.company;

import java.awt.*;

public class Circle implements Shape {
    private double radius;
    private Point center;

    public Circle(Point center, double circleRadius) throws Exception {
        this.setCenter(center);
        this.setRadius(circleRadius);
    }

    @Override
    public void draw(Graphics graphics) {
        Point center = this.getCenter();
        graphics.drawArc((int)center.getX(), (int)center.getY(), (int)(this.getRadius() * 2.0), (int)(this.getRadius() * 2.0), 0, 360);
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(getRadius(), 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * getRadius();

    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) throws Exception {
        if (radius <= 0) {
            throw new Exception("Radius cannot be less or equal 0");
        }

        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public String getGid() {
        return ShapeType.CIRCLE.toString();
    }

    @Override
    public double getWidth() {
        return this.getRadius() * 2.0;
    }

    @Override
    public double getHeight() {
        return this.getWidth();
    }

    @Override
    public Point calcLeftTopPoint() {
        Point center = this.getCenter();
        return new Point(center.getX(), center.getY());
    }
}
