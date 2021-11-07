package com.company.command;

import com.company.Application;
import com.company.ToolPanel;
import com.company.shape.ShapeCompound;

import java.awt.*;

public class ChangeShapeBackgroundColorCommand implements Command {

    private ToolPanel toolPanel;

    public ChangeShapeBackgroundColorCommand(ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
    }

    @Override
    public void execute() {
        Application frame = Application.getInstance();

        Color color = toolPanel.getLastPickedColor();
        if (color != null) {
            for (ShapeCompound shapeCompound : frame.getSelectedCompounds()) {
                shapeCompound.setBackgroundColor(color);
            }

            frame.repaint();
        }
    }
}
