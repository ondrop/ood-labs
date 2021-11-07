package com.company.listener;

import com.bric.colorpicker.ColorPickerDialog;
import com.company.Application;
import com.company.ToolPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorderColorPickerListener implements ActionListener {

    public BorderColorPickerListener() {}

    @Override
    public void actionPerformed(ActionEvent e) {
        ColorPickerDialog dialog = new ColorPickerDialog();
        Color color = ColorPickerDialog.showDialog(dialog,"Border color picker", Color.BLUE, false);

        Application frame = Application.getInstance();

        ToolPanel toolPanel = frame.getToolPanel();
        toolPanel.setLastPickedColor(color);
        toolPanel.setBorderColor();

        frame.repaint();
    }
}
