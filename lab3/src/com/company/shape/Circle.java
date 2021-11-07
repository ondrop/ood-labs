package com.company.shape;

import java.awt.*;
import java.awt.Shape;

public class Circle extends BaseShape {
    private double radius;
    private Point center;

    public Circle(Point center, double circleRadius) throws Exception {
        this.setCenter(center);
        this.setRadius(circleRadius);
    }

    public Circle() throws Exception {
        this.setCenter(new Point(50, 50));
        this.setRadius(50);
    }

    @Override
    public void draw(Graphics graphics) {
        Point center = this.getCenter();
        Graphics2D graphics2D = (Graphics2D) graphics;

        Point leftTop = calcLeftTopPoint();
        graphics2D.setColor(getBackgroundColor());
        graphics2D.fillOval((int)leftTop.getX(), (int)leftTop.getY(), (int)getWidth(), (int)getHeight());

        graphics2D.setColor(getBorderColor());
        graphics2D.setStroke(new BasicStroke(getBorderWidth()));
        graphics2D.drawArc((int)(center.getX() - getRadius()), (int)(center.getY() - getRadius()), (int)(this.getRadius() * 2.0), (int)(this.getRadius() * 2.0), 0, 360);
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
        return new Point(center.getX() - getRadius(), center.getY() - getRadius());
    }

    @Override
    public boolean isInsideBounds(double x, double y) {
        Point center = getCenter();
        return getRadius() > Math.sqrt(Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2));
    }

    @Override
    public void translate(int x, int y) {
        Point center = this.getCenter();
        this.setCenter(new Point(center.getX() + x, center.getY() + y));
    }
}
