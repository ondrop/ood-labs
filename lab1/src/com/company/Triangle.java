package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Triangle implements Shape {
    private Point vertex1;
    private Point vertex2;
    private Point vertex3;

    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        this.setVertex1(vertex1);
        this.setVertex2(vertex2);
        this.setVertex3(vertex3);
    }

    @Override
    public void draw(Graphics graphics) {
        Point vertex1 = this.getVertex1();
        Point vertex2 = this.getVertex2();
        Point vertex3 = this.getVertex3();
        int[] xPoints = {(int)vertex1.getX(), (int)vertex2.getX(), (int)vertex3.getX()};
        int[] yPoints = {(int)vertex1.getY(), (int)vertex2.getY(), (int)vertex3.getY()};
        graphics.drawPolygon(
                xPoints,
                yPoints,
                3
        );
    }

    @Override
    public double getArea() {
        double halfOfPerimeter = this.getPerimeter();
        double ABLength = this.getLineWidth(this.getVertex1(), this.getVertex2());
        double ACLength = this.getLineWidth(this.getVertex1(), this.getVertex3());
        double BCLength = this.getLineWidth(this.getVertex2(), this.getVertex3());

        return Math.sqrt(halfOfPerimeter * (halfOfPerimeter - ABLength) * (halfOfPerimeter - ACLength) * (halfOfPerimeter - BCLength));
    }

    @Override
    public double getPerimeter() {
        return this.getLineWidth(this.getVertex1(), this.getVertex2())
                + this.getLineWidth(this.getVertex1(), this.getVertex3())
                + this.getLineWidth(this.getVertex2(), this.getVertex3());
    }

    public Point getVertex1() {
        return vertex1;
    }

    public void setVertex1(Point vertex1) {
        this.vertex1 = vertex1;
    }

    public Point getVertex2() {
        return vertex2;
    }

    public void setVertex2(Point vertex2) {
        this.vertex2 = vertex2;
    }

    public Point getVertex3() {
        return vertex3;
    }

    public void setVertex3(Point vertex3) {
        this.vertex3 = vertex3;
    }

    private double getLineWidth(Point vertex1, Point vertex2) {
        return Math.sqrt(Math.pow(vertex1.getX() - vertex2.getX(), 2) + Math.pow(vertex1.getY() - vertex2.getY(), 2));
    }

    public String getGid() {
        return ShapeType.TRIANGLE.toString();
    }

    @Override
    public double getWidth() {
        ArrayList<Double> X = new ArrayList<>();
        X.add(this.getVertex1().getX());
        X.add(this.getVertex2().getX());
        X.add(this.getVertex3().getX());
        Collections.sort(X);
        return Math.abs(X.get(2) - X.get(0));
    }

    @Override
    public double getHeight() {
        ArrayList<Double> Y = new ArrayList<>();
        Y.add(this.getVertex1().getY());
        Y.add(this.getVertex2().getY());
        Y.add(this.getVertex3().getY());
        Collections.sort(Y);
        return Math.abs(Y.get(2) - Y.get(0));
    }

    @Override
    public Point calcLeftTopPoint() {
        ArrayList<Double> X = new ArrayList<>();
        X.add(this.getVertex1().getX());
        X.add(this.getVertex2().getX());
        X.add(this.getVertex3().getX());
        Collections.sort(X);

        ArrayList<Double> Y = new ArrayList<>();
        Y.add(this.getVertex1().getY());
        Y.add(this.getVertex2().getY());
        Y.add(this.getVertex3().getY());
        Collections.sort(Y);

        return new Point(X.get(0), Y.get(0));
    }
}
