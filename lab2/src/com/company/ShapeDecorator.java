package com.company;

import java.awt.*;

public class ShapeDecorator implements Shape {
    private final Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics graphics) {
        this.shape.draw(graphics);

        //ShapeDecorator draw method realization
        final float[] dash1 = {10.0f};
        Graphics2D graphics2D = (Graphics2D) graphics;
        Stroke defaultStroke = graphics2D.getStroke();
        BasicStroke dashed = new BasicStroke(2.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        Point leftTop = this.shape.calcLeftTopPoint();
        graphics2D.setStroke(dashed);
        graphics2D.drawRect((int)leftTop.getX(), (int)leftTop.getY(), (int)this.shape.getWidth(), (int)this.shape.getHeight());
        graphics2D.setStroke(defaultStroke);
    }

    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }

    @Override
    public double getPerimeter() {
        return 2 * (getWidth() + getHeight());
    }

    @Override
    public String getGid() {
        return "ShapeDecorator";
    }

    @Override
    public double getWidth() {
        return shape.getWidth();
    }

    @Override
    public double getHeight() {
        return shape.getHeight();
    }

    @Override
    public Point calcLeftTopPoint() {
        return this.shape.calcLeftTopPoint();
    }

    @Override
    public boolean isInsideBounds(double x, double y) {
        return this.shape.isInsideBounds(x, y);
    }

    @Override
    public void translate(int x, int y) {
        this.shape.translate(x, y);
    }
}
