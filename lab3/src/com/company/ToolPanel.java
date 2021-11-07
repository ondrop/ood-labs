package com.company;

import com.company.listener.AddShapeActionListener;
import com.company.listener.BackgroundColorPickerListener;
import com.company.listener.BorderColorPickerListener;
import com.company.listener.BorderWidthListener;
import com.company.shape.Circle;
import com.company.shape.Rectangle;
import com.company.shape.Triangle;
import com.company.state.ComponentsIsNotSelectedState;
import com.company.state.ComponentsIsSelectedState;
import com.company.state.State;

import javax.swing.*;
import java.awt.*;

public class ToolPanel extends JPanel {

    private Color lastPickedColor;
    private int lastPickedBorderWidth;

    private State componentsIsSelectedState;
    private State componentsIsNotSelectedState;

    private State state;

    public ToolPanel() {

        componentsIsSelectedState = new ComponentsIsSelectedState(this);
        componentsIsNotSelectedState = new ComponentsIsNotSelectedState(this);

        state = componentsIsNotSelectedState;

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.GRAY);

        JButton addCircleBtn = new JButton("Add circle");
        addCircleBtn.setFocusable(false);
        addCircleBtn.addActionListener(new AddShapeActionListener(Circle.class.getName()));
        add(addCircleBtn);

        JButton addRectangleBtn = new JButton("Add rectangle");
        addRectangleBtn.setFocusable(false);
        addRectangleBtn.addActionListener(new AddShapeActionListener(Rectangle.class.getName()));
        add(addRectangleBtn);

        JButton addTriangleBtn = new JButton("Add triangle");
        addTriangleBtn.setFocusable(false);
        addTriangleBtn.addActionListener(new AddShapeActionListener(Triangle.class.getName()));
        add(addTriangleBtn);

        JButton pickColorBackgroundBtn = new JButton("Shape color");
        pickColorBackgroundBtn.setFocusable(false);
        pickColorBackgroundBtn.addActionListener(new BackgroundColorPickerListener());
        add(pickColorBackgroundBtn);

        JButton pickColorBorderBtn = new JButton("Shape's border color");
        pickColorBorderBtn.setFocusable(false);
        pickColorBorderBtn.addActionListener(new BorderColorPickerListener());
        add(pickColorBorderBtn);

        JButton borderWidthBtn = new JButton("Shape's border width color");
        borderWidthBtn.setFocusable(false);
        borderWidthBtn.addActionListener(new BorderWidthListener());
        add(borderWidthBtn);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Color getLastPickedColor() {
        return lastPickedColor;
    }

    public void setLastPickedColor(Color lastPickedColor) {
        this.lastPickedColor = lastPickedColor;
    }

    public void setLastPickedBorderWidth(int lastPickedBorderWidth) {
        this.lastPickedBorderWidth = lastPickedBorderWidth;
    }

    public int getLastPickedBorderWidth() {
        return lastPickedBorderWidth;
    }

    public void setBackgroundColor() {
        state.setBackgroundColor();
    }

    public void setBorderColor() {
        state.setBorderColor();
    }

    public void setBorderWidth() {
        state.setBorderWidth();
    }

    public State getComponentsIsSelectedState() {
        return componentsIsSelectedState;
    }

    public State getComponentsIsNotSelectedState() {
        return componentsIsNotSelectedState;
    }
}
