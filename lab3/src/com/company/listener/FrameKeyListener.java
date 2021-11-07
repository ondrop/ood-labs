package com.company.listener;

import com.company.Application;
import com.company.shape.ShapeCompound;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FrameKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("test");
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        System.out.println("test");
        Application frame = Application.getInstance();
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            frame.changeShiftPressed(true);
            System.out.println(Application.getInstance().getShiftPressed());
        }

        if ((e.getKeyCode() == KeyEvent.VK_G) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            ArrayList<ShapeCompound> selectedShapes = new ArrayList<>();
            for (ShapeCompound shape : frame.getShapes()) {
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

                    frame.removeShape(selectedShape);
                }

                newCompound.changeSelection(true);
                frame.addShape(newCompound);
                frame.repaint();
            }
        }

        if ((e.getKeyCode() == KeyEvent.VK_U) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            ArrayList<ShapeCompound> selectedCompounds = new ArrayList<>();
            for (ShapeCompound shape : frame.getShapes()) {
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
                        frame.addShape(shapeWrapper);
                    }

                    frame.removeShape(selectedCompound);
                    frame.repaint();
                }
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        Application.getInstance().changeShiftPressed(false);
        System.out.println(Application.getInstance().getShiftPressed());
    }
}
