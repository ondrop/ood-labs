package com.company.listener;

import com.company.Application;
import com.company.memento.Caretaker;
import com.company.shape.ShapeCompound;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FrameKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        Application frame = Application.getInstance();
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            frame.changeShiftPressed(true);
        }

        if ((e.getKeyCode() == KeyEvent.VK_G) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            ArrayList<ShapeCompound> selectedCompounds = frame.getSelectedCompounds();

            if (selectedCompounds.size() > 1) {
                ShapeCompound newCompound = new ShapeCompound();
                for (ShapeCompound selectedCompound : selectedCompounds) {
                    selectedCompound.changeSelection(false);
                    for (com.company.shape.Shape shape : selectedCompound.getChildren()) {
                        newCompound.add(shape);
                    }

                    frame.removeShape(selectedCompound);
                }

                newCompound.changeSelection(true);
                frame.addShape(newCompound);
                frame.repaint();
            }
        }

        if ((e.getKeyCode() == KeyEvent.VK_U) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            ArrayList<ShapeCompound> selectedCompounds = frame.getSelectedCompounds();

            if (selectedCompounds.size() > 0) {
                boolean ungrouped = false;
                for (ShapeCompound selectedCompound : selectedCompounds) {
                    if (selectedCompound.getChildren().size() > 1) {
                        ungrouped = true;
                        for (com.company.shape.Shape shape : selectedCompound.getChildren()) {
                            ShapeCompound shapeWrapper = new ShapeCompound(shape);
                            shapeWrapper.changeSelection(true);
                            frame.addShape(shapeWrapper);
                        }

                        frame.removeShape(selectedCompound);
                    }
                }

                if (ungrouped) {
                    frame.repaint();
                }
            }
        }

        if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            frame.getCaretaker().undo();
            frame.repaint();
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        Application.getInstance().changeShiftPressed(false);
    }
}
