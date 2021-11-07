package com.company.command;

import com.company.Application;
import com.company.ToolPanel;
import com.company.shape.ShapeCompound;

public class ChangeShapeBorderWidthCommand implements Command {

    private ToolPanel toolPanel;

    public ChangeShapeBorderWidthCommand(ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
    }

    @Override
    public void execute() {
        Application frame = Application.getInstance();

        int width = toolPanel.getLastPickedBorderWidth();
        if (width <= 0) {
            return;
        }

        for (ShapeCompound shapeCompound : frame.getSelectedCompounds()) {
            shapeCompound.setBorderWidth(width);
        }

        frame.repaint();
    }
}
