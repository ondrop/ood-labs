package com.company.listener;

import com.company.Application;
import com.company.shape.ShapeCompound;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {
    private Application jFrame;

    public ClickListener(Application jFrame) {
        this.jFrame = jFrame;
    }

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
