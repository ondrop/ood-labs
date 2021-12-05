package com.company.state;

import com.company.ToolPanel;

public class ComponentsIsNotSelectedComponentState implements ComponentState {

    private ToolPanel toolPanel;

    public ComponentsIsNotSelectedComponentState(ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
    }

    @Override
    public void setBackgroundColor() {}

    @Override
    public void setBorderColor() {}

    @Override
    public void setBorderWidth() {}
}
