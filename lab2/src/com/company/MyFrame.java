package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyFrame extends JFrame {
    private boolean shiftPressed = false;
    private Point prevPt;
    protected ArrayList<ShapeCompound> shapes;
    private ShapeCompound choosedComponent;

    public MyFrame(String frameTitle, int width, int height, ArrayList<ShapeCompound> shapes) {
        this.shapes = shapes;
        this.setSize(width, height);
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addMouseListener(new ClickListener());
        this.addKeyListener(new KeyListener());
        this.addMouseMotionListener(new DragListener());
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for (ShapeCompound shapeCompound : this.shapes) {
            shapeCompound.draw(graphics);
        }
    }

    private class ClickListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
            boolean inShape = false;
            for (int i = shapes.size() - 1; i >= 0; i--) {
                ShapeCompound shapeCompound = shapes.get(i);
                inShape = shapeCompound.isInsideBounds(e.getPoint().getX(), e.getPoint().getY());
                if (inShape) {
                    choosedComponent = shapeCompound;
                    if (!shiftPressed) {
                        for (ShapeCompound shape : shapes) {
                            shape.changeSelection(false);
                        }
                    }

                    boolean selection = true;
                    if (shiftPressed) {
                        selection = !shapeCompound.isSelected();
                    }

                    shapeCompound.changeSelection(selection);
                    break;
                }
            }


            if (!inShape) {
                for (ShapeCompound shape : shapes) {
                    shape.changeSelection(false);
                }
            }

            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            choosedComponent = null;
        }
    }

    private class DragListener extends MouseMotionAdapter {

        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();

            if (choosedComponent != null) {
                choosedComponent.translate(
                        (int) (currentPt.getX() - prevPt.getX()),
                        (int) (currentPt.getY() - prevPt.getY())
                );
            }

            prevPt = currentPt;
            repaint();
        }
    }

    private class KeyListener implements java.awt.event.KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public synchronized void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                shiftPressed = true;
            }

            if ((e.getKeyCode() == KeyEvent.VK_G) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                ArrayList<ShapeCompound> selectedShapes = new ArrayList<>();
                for (ShapeCompound shape : shapes) {
                    if (shape.isSelected()) {
                        selectedShapes.add(shape);
                    }
                }

                if (selectedShapes.size() > 1) {
                    ShapeCompound newCompound = new ShapeCompound();
                    for (ShapeCompound selectedShape : selectedShapes) {
                        selectedShape.changeSelection(false);
                        for (Shape shape : selectedShape.children) {
                            newCompound.add(shape);
                        }

                        shapes.remove(selectedShape);
                    }

                    newCompound.changeSelection(true);
                    shapes.add(newCompound);
                    repaint();
                }
            }

            if ((e.getKeyCode() == KeyEvent.VK_U) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                ArrayList<ShapeCompound> selectedCompounds = new ArrayList<>();
                for (ShapeCompound shape : shapes) {
                    if (shape.isSelected()) {
                        selectedCompounds.add(shape);
                    }
                }

                if (selectedCompounds.size() == 1) {
                    ShapeCompound selectedCompound = selectedCompounds.get(0);
                    if (selectedCompound.children.size() > 1) {
                        for (Shape shape : selectedCompound.children) {
                            ShapeCompound shapeWrapper = new ShapeCompound(shape);
                            shapeWrapper.changeSelection(true);
                            shapes.add(shapeWrapper);
                        }

                        shapes.remove(selectedCompound);
                        repaint();
                    }
                }
            }
        }

        @Override
        public synchronized void keyReleased(KeyEvent e) {
            shiftPressed = false;
        }
    }
}
