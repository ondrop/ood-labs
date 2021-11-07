package com.company.command;

import com.company.Application;
import com.company.ToolPanel;
import com.company.shape.ShapeCompound;

import java.awt.*;

public class ChangeBorderBackgroundColorCommand implements Command {

    @Override
    public void execute() {
        Application frame = Application.getInstance();

        Color color = ToolPanel.getLastPickedColor();
        for (ShapeCompound shapeCompound : frame.getSelectedCompounds()) {
            shapeCompound.setBorderColor(color);
        }

        frame.repaint();
    }
}
