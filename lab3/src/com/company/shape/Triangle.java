package com.company.shape;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Triangle extends BaseShape {
    final String POINTS_FIELD = "points";

    private Point vertex1;
    private Point vertex2;
    private Point vertex3;

    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        this.setVertex1(vertex1);
        this.setVertex2(vertex2);
        this.setVertex3(vertex3);
    }
    
    public Triangle() {
        this.setVertex1(new Point(0, 100));
        this.setVertex2(new Point(50, 0));
        this.setVertex3(new Point(100, 100));
    }

    @Override
    public void draw(Graphics graphics) {
        Point vertex1 = this.getVertex1();
        Point vertex2 = this.getVertex2();
        Point vertex3 = this.getVertex3();
        int[] xPoints = {(int)vertex1.getX(), (int)vertex2.getX(), (int)vertex3.getX()};
        int[] yPoints = {(int)vertex1.getY(), (int)vertex2.getY(), (int)vertex3.getY()};

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(getBackgroundColor());
        graphics2D.fillPolygon(
                xPoints,
                yPoints,
                3
        );

        graphics2D.setColor(getBorderColor());
        graphics2D.setStroke(new BasicStroke(getBorderWidth()));

        graphics2D.drawPolygon(
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
        return Triangle.class.getName();
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

    @Override
    public boolean isInsideBounds(double x, double y) {
        Point vertex1 = this.getVertex1();
        Point vertex2 = this.getVertex2();
        Point vertex3 = this.getVertex3();

        double p1 = (vertex1.getX() - x) * (vertex2.getY() - y) - (vertex2.getX() - x) * (vertex1.getY() - y);
        double p2 = (vertex2.getX() - x) * (vertex3.getY() - vertex2.getY()) - (vertex3.getX() - vertex2.getX()) * (vertex2.getY() - y);
        double p3 = (vertex3.getX() - x) * (vertex1.getY() - vertex3.getY()) - (vertex1.getX() - vertex3.getX()) * (vertex3.getY() - y);

        return (p1 > 0 && p2 > 0 && p3 > 0) || (p1 < 0 && p2 < 0 && p3 < 0);
    }

    @Override
    public void translate(int x, int y) {
        Point vertex1 = this.getVertex1();
        this.setVertex1(new Point(vertex1.getX() + x, vertex1.getY() + y));
        Point vertex2 = this.getVertex2();
        this.setVertex2(new Point(vertex2.getX() + x, vertex2.getY() + y));
        Point vertex3 = this.getVertex3();
        this.setVertex3(new Point(vertex3.getX() + x, vertex3.getY() + y));
    }

    @Override
    public JSONObject getCoordinates() {
        ArrayList<JSONObject> points = new ArrayList<>();

        JSONObject vertex1 = new JSONObject();
        vertex1.put("X", getVertex1().getX());
        vertex1.put("Y", getVertex1().getY());
        points.add(vertex1);

        JSONObject vertex2 = new JSONObject();
        vertex2.put("X", getVertex2().getX());
        vertex2.put("Y", getVertex2().getY());
        points.add(vertex2);

        JSONObject vertex3 = new JSONObject();
        vertex3.put("X", getVertex3().getX());
        vertex3.put("Y", getVertex3().getY());
        points.add(vertex3);

        JSONObject coordinates = new JSONObject();

        coordinates.put(POINTS_FIELD, points);

        return coordinates;
    }

    @Override
    public void setData(JSONObject shapeData) {
        super.setData(shapeData);
        try {
            JSONObject coordinates = shapeData.getJSONObject(COORDINATES_FIELD);
            JSONArray points = coordinates.getJSONArray(POINTS_FIELD);
            JSONObject vertex1 = (JSONObject) points.get(0);
            Point newVertex1 = new Point(vertex1.getDouble("X"), vertex1.getDouble("Y"));
            setVertex1(newVertex1);

            JSONObject vertex2 = (JSONObject) points.get(1);
            Point newVertex2 = new Point(vertex2.getDouble("X"), vertex2.getDouble("Y"));
            setVertex2(newVertex2);

            JSONObject vertex3 = (JSONObject) points.get(2);
            Point newVertex3 = new Point(vertex3.getDouble("X"), vertex3.getDouble("Y"));
            setVertex3(newVertex3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
