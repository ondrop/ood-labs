package com.company.shape;

import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class ShapeCompound extends BaseShape {

    protected ArrayList<Shape> children = new ArrayList<Shape>();
    private boolean isSelected = false;

    public ShapeCompound(Shape... components) {
        add(components);
    }

    public ShapeCompound(boolean isSelected, Shape... components) {
        add(components);
        this.isSelected = isSelected;
    }

    public void add(Shape component) {
        children.add(component);
    }

    public void add(Shape... components) {
        children.addAll(Arrays.asList(components));
    }

    public void remove(Shape child) {
        children.remove(child);
    }

    public void remove(Shape... components) {
        children.removeAll(Arrays.asList(components));
    }

    public void clear() {
        children.clear();
    }

    public ArrayList<Shape> getChildren() {
        return children;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void changeSelection(boolean selected) {
        this.isSelected = selected;
    }

    @Override
    public void draw(Graphics graphics) {
        for (Shape child : children) {
            child.draw(graphics);
        }

        //ShapeComponent draw method realization like decorator
        if (!isSelected()) {
            return;
        }

        final float[] dash1 = {10.0f};
        Graphics2D graphics2D = (Graphics2D) graphics;
        Stroke defaultStroke = graphics2D.getStroke();
        BasicStroke dashed = new BasicStroke(2.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        com.company.shape.Point leftTop = this.calcLeftTopPoint();
        graphics2D.setStroke(dashed);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect((int)leftTop.getX(), (int)leftTop.getY(), (int)this.getWidth(), (int)this.getHeight());
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
        return "ShapeCompound";
    }

    @Override
    public double getWidth() {
        double maxWidth = 0;
        com.company.shape.Point leftTop = calcLeftTopPoint();
        for (Shape child : children) {
            double childsRelativeX = child.calcLeftTopPoint().getX() - leftTop.getX();
            double childWidth = childsRelativeX + child.getWidth();
            if (childWidth > maxWidth) {
                maxWidth = childWidth;
            }
        }

        return maxWidth;
    }

    @Override
    public double getHeight() {
        double maxHeight = 0;
        com.company.shape.Point leftTop = calcLeftTopPoint();
        for (Shape child : children) {
            double childsRelativeY = child.calcLeftTopPoint().getY() - leftTop.getY();
            double childHeight = childsRelativeY + child.getHeight();
            if (childHeight > maxHeight) {
                maxHeight = childHeight;
            }
        }

        return maxHeight;
    }

    @Override
    public com.company.shape.Point calcLeftTopPoint() {
        if (children.size() == 0) {
            return new com.company.shape.Point(0, 0);
        }

        com.company.shape.Point leftTop = children.get(0).calcLeftTopPoint();
        double x = leftTop.getX();
        double y = leftTop.getY();
        for (Shape child : children) {
            com.company.shape.Point childLeftTop = child.calcLeftTopPoint();
            if (childLeftTop.getX() < x) {
                x = childLeftTop.getX();
            }

            if (childLeftTop.getY() < y) {
                y = childLeftTop.getY();
            }
        }

        return new com.company.shape.Point(x, y);
    }

    @Override
    public boolean isInsideBounds(double x, double y) {
        ListIterator<Shape> list = children.listIterator(children.size());

        while (list.hasPrevious()) {
            if (list.previous().isInsideBounds(x, y)) {
                return true;
            }
        }

        return false;
    }

    public void translate(int x, int y) {
        for (Shape child : children) {
            child.translate(x, y);
        }
    }

    @Override
    public void setBackgroundColor(Color color) {
        for (Shape child : children) {
            child.setBackgroundColor(color);
        }
    }

    @Override
    public void setBorderColor(Color borderColor) {
        for (Shape child : children) {
            child.setBorderColor(borderColor);
        }
    }

    @Override
    public void setBorderWidth(int borderWidth) {
        for (Shape child : children) {
            child.setBorderWidth(borderWidth);
        }
    }

    @Override
    public JSONObject getCoordinates() {
        return null;
    }

    @Override
    public JSONObject getState() {
        JSONObject states = new JSONObject();
        ArrayList<JSONObject> childShapes = new ArrayList<>();
        for (Shape child : children) {
            childShapes.add(child.getState());
        }

        states.put(CHILDREN_FIELD, childShapes);

        return states;
    }
}
