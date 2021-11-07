package com.company.listener;

import com.company.Application;
import com.company.shape.ShapeCompound;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DragListener extends MouseMotionAdapter {

    public void mouseDragged(MouseEvent e) {
        Application frame = Application.getInstance();
        Point currentPt = e.getPoint();

        ShapeCompound choosedComponent = frame.getChoosedComponent();
        if (choosedComponent != null) {
            Point prevPt = frame.getPrevPt();
            choosedComponent.translate(
                    (int) (currentPt.getX() - prevPt.getX()),
                    (int) (currentPt.getY() - prevPt.getY())
            );
        }

        frame.setPrevPt(currentPt);
        frame.repaint();
    }
}
