package com.company.state;

import com.company.ToolPanel;
import com.company.command.ChangeShapeBackgroundColorCommand;
import com.company.command.ChangeShapeBorderColorCommand;
import com.company.command.ChangeShapeBorderWidthCommand;

public class ComponentsIsSelectedState implements State {

    private ToolPanel toolPanel;

    private ChangeShapeBorderColorCommand changeShapeBorderColorCommand;
    private ChangeShapeBorderWidthCommand changeShapeBorderWidthCommand;
    private ChangeShapeBackgroundColorCommand changeShapeBackgroundColorCommand;

    public ComponentsIsSelectedState(ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
        changeShapeBackgroundColorCommand = new ChangeShapeBackgroundColorCommand(toolPanel);
        changeShapeBorderColorCommand = new ChangeShapeBorderColorCommand(toolPanel);
        changeShapeBorderWidthCommand = new ChangeShapeBorderWidthCommand(toolPanel);
    }

    @Override
    public void setBackgroundColor() {
        changeShapeBackgroundColorCommand.execute();
    }

    @Override
    public void setBorderColor() {
        changeShapeBorderColorCommand.execute();
    }

    @Override
    public void setBorderWidth() {
        changeShapeBorderWidthCommand.execute();
    }
}
