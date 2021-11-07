package com.company.listener;

import com.company.Application;
import com.company.ToolPanel;
import com.company.shape.ShapeCompound;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ClickListener extends MouseAdapter {

    public ClickListener() {}

    public void mousePressed(MouseEvent e) {
        Application frame = Application.getInstance();
        ToolPanel toolPanel = frame.getToolPanel();

        frame.setPrevPt(e.getPoint());
        boolean inShape = false;
        ArrayList<ShapeCompound> shapes = frame.getShapes();
        for (int i = shapes.size() - 1; i >= 0; i--) {
            ShapeCompound shapeCompound = shapes.get(i);
            inShape = shapeCompound.isInsideBounds(e.getPoint().getX(), e.getPoint().getY());
            if (inShape) {
                toolPanel.setState(toolPanel.getComponentsIsSelectedState());

                frame.setChoosedComponent(shapeCompound);
                if (!frame.getShiftPressed()) {
                    for (ShapeCompound shape : shapes) {
                        shape.changeSelection(false);
                    }
                }

                boolean selection = true;
                if (frame.getShiftPressed()) {
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

            toolPanel.setState(toolPanel.getComponentsIsNotSelectedState());
        }

        frame.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        Application.getInstance().setChoosedComponent(null);
    }
}
