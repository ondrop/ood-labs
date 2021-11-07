package com.company.state;

import com.company.ToolPanel;

public class ComponentsIsNotSelectedState implements State {

    private ToolPanel toolPanel;

    public ComponentsIsNotSelectedState(ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
    }

    @Override
    public void setBackgroundColor() {}

    @Override
    public void setBorderColor() {}

    @Override
    public void setBorderWidth() {}
}
