package com.company;

import com.company.listener.FrameKeyListener;
import com.company.shape.ShapeCompound;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;

public class Application extends JFrame {

    private static Application uniqueInstance;

    private boolean shiftPressed = false;
    private Point prevPt;
    protected ArrayList<ShapeCompound> shapes = new ArrayList<ShapeCompound>();
    private ShapeCompound choosedComponent;
    private ShapePanel shapePanel;
    private ToolPanel toolPanel;

    private Application(String frameTitle, int width, int height) {
        super();
        run(frameTitle, width, height);
    }

    private void run(String frameTitle, int width, int height) {
        this.setLayout(new BorderLayout());

        toolPanel = new ToolPanel();
        add(toolPanel, BorderLayout.NORTH);

        shapePanel = new ShapePanel();
        shapePanel.setBackground(Color.WHITE);
        add(shapePanel, BorderLayout.CENTER);

        this.setSize(width, height);
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addKeyListener(new FrameKeyListener());
    }

    public static Application getInstance() {
        if (uniqueInstance == null) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();

            uniqueInstance = new Application("Frame", (int)width, (int)height);
        }

        return uniqueInstance;
    }

    public void addShape(ShapeCompound shapeCompound) {
        this.shapes.add(shapeCompound);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        this.shapePanel.setShapes(this.shapes);
    }

    public Point getPrevPt() {
        return prevPt;
    }

    public void setPrevPt(Point newPrevPt) {
        prevPt = newPrevPt;
    }

    public boolean getShiftPressed() {
        return shiftPressed;
    }

    public void changeShiftPressed(boolean newShiftPressed) {
        shiftPressed = newShiftPressed;
    }

    public ArrayList<ShapeCompound> getShapes() {
        return shapes;
    }

    public ShapeCompound getChoosedComponent() {
        return choosedComponent;
    }

    public void setChoosedComponent(ShapeCompound newChoosedComponent) {
        choosedComponent = newChoosedComponent;
    }

    public void removeShape(ShapeCompound shapeCompound) {
        shapes.remove(shapeCompound);
    }

    public ArrayList<ShapeCompound> getSelectedCompounds() {
        ArrayList<ShapeCompound> selectedCompounds = new ArrayList<>();
        for (ShapeCompound shapeCompound : shapes) {
            if (shapeCompound.isSelected()) {
                selectedCompounds.add(shapeCompound);
            }
        }

        return selectedCompounds;
    }

    public ShapePanel getShapePanel() {
        return shapePanel;
    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }
}
