package com.company.listener;

import com.bric.colorpicker.ColorPickerDialog;
import com.company.Application;
import com.company.ToolPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackgroundColorPickerListener implements ActionListener {

    public BackgroundColorPickerListener() {}

    @Override
    public void actionPerformed(ActionEvent e) {
        ColorPickerDialog dialog = new ColorPickerDialog();
        Color color = ColorPickerDialog.showDialog(dialog,"Color picker", Color.BLUE, false);
        System.out.println(color);

        Application frame = Application.getInstance();

        ToolPanel toolPanel = frame.getToolPanel();
        toolPanel.setLastPickedColor(color);
        toolPanel.setState(toolPanel.getComponentsIsNotSelectedState());
        toolPanel.changeColor();

        frame.repaint();
    }
}
