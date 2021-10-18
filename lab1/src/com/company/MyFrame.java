package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyFrame extends JFrame {
    protected ArrayList<Shape> shapes;

    public MyFrame(String frameTitle, int width, int height, ArrayList<Shape> shapes) {
        this.setSize(width, height);
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.shapes = shapes;
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        for (Shape shape : this.shapes) {
            shape.draw(graphics);
        }
    }
}
