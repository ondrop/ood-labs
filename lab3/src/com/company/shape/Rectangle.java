package com.company.shape;

import java.awt.*;

public class Rectangle extends BaseShape {
    private Point leftTop;
    private Point rightBottom;

    public Rectangle(Point leftTop, Point rightBottom) {
        this.setLeftTop(leftTop);
        this.setRightBottom(rightBottom);
    }
    
    public Rectangle() {
        this.setLeftTop(new Point(0, 0));
        this.setRightBottom(new Point(100, 100));
    }

    @Override
    public void draw(Graphics graphics) {
        Point leftTop = this.getLeftTop();
        Point rightBottom = this.getRightBottom();
        int width = (int)Math.abs(rightBottom.getX() - leftTop.getX());
        int height = (int)Math.abs(leftTop.getY() - rightBottom.getY());

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(getBackgroundColor());
        graphics2D.fillRect((int)leftTop.getX(), (int)leftTop.getY(), width, height);

        graphics2D.setColor(getBorderColor());
        graphics2D.setStroke(new BasicStroke(getBorderWidth()));
        graphics2D.drawRect((int)leftTop.getX(), (int)leftTop.getY(), width, height);
    }

    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }

    @Override
    public double getPerimeter() {
        return 2 * (getWidth() + getHeight());
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public void setLeftTop(Point leftTop) {
        this.leftTop = leftTop;
    }

    public Point getRightBottom() {
        return rightBottom;
    }

    public void setRightBottom(Point rightBottom) {
        this.rightBottom = rightBottom;
    }

    public double getWidth() {
        return Math.abs(getRightBottom().getX() - getLeftTop().getX());
    }

    public double getHeight() {
        return Math.abs(getLeftTop().getY() - getRightBottom().getY());
    }

    @Override
    public Point calcLeftTopPoint() {
        return getLeftTop();
    }

    @Override
    public boolean isInsideBounds(double x, double y) {
        Point leftTop = calcLeftTopPoint();
        return x > leftTop.getX() && x < (leftTop.getX() + getWidth()) &&
                y > leftTop.getY() && y < (leftTop.getY() + getHeight());
    }

    @Override
    public String getGid() {
        return ShapeType.RECTANGLE.toString();
    }

    @Override
    public void translate(int x, int y) {
        Point leftTop = this.getLeftTop();
        this.setLeftTop(new Point(leftTop.getX() + x, leftTop.getY() + y));
        Point rightBottom = this.getRightBottom();
        this.setRightBottom(new Point(rightBottom.getX() + x, rightBottom.getY() + y));
    }
}
