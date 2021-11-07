package com.company.frame;

import com.company.ApplicationFacade;
import com.company.shape.ShapeCompound;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

public class Application extends JFrame {
    private static Application uniqueInstance = new Application("Frame", 1920, 1080);

    private boolean shiftPressed = false;
    private Point prevPt;
    protected ArrayList<ShapeCompound> shapes;
    private ShapeCompound choosedComponent;

    private Application(String frameTitle, int width, int height) {
        super();

        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JButton button = new JButton("Обычная кнопка");
        JButton button1 = new JButton("Обычная кнопка 1");
        container.add(button);
        container.add(button1);

        this.setSize(width, height);
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addMouseListener(new ClickListener());
        this.addKeyListener(new KeyListener());
        this.addMouseMotionListener(new DragListener());
    }

    public static Application getInstance() {
        return uniqueInstance;
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
                        for (com.company.shape.Shape shape : selectedShape.getChildren()) {
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
                    if (selectedCompound.getChildren().size() > 1) {
                        for (com.company.shape.Shape shape : selectedCompound.getChildren()) {
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
