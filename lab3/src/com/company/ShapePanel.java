package com.company;

import com.company.listener.ClickListener;
import com.company.listener.DragListener;
import com.company.shape.ShapeCompound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShapePanel extends JPanel {

    protected ArrayList<ShapeCompound> shapes = new ArrayList<>();

    public ShapePanel(ArrayList<ShapeCompound> shapes) {
        this.shapes = shapes;
        this.addMouseListener(new ClickListener());
        this.addMouseMotionListener(new DragListener());
    }

    public ShapePanel() {
        this.addMouseListener(new ClickListener());
        this.addMouseMotionListener(new DragListener());
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (ShapeCompound shapeCompound : this.shapes) {
            shapeCompound.draw(graphics);
        }
    }

    public void setShapes(ArrayList<ShapeCompound> shapes) {
        this.shapes = shapes;
    }
}
