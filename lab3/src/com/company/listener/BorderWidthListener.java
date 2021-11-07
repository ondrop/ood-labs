package com.company.listener;

import com.company.Application;
import com.company.BorderWidthDialog;
import com.company.ToolPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorderWidthListener implements ActionListener {

    public BorderWidthListener() {}

    @Override
    public void actionPerformed(ActionEvent e) {
        BorderWidthDialog dialog = new BorderWidthDialog();
        int width = BorderWidthDialog.showDialog(dialog,"Border width select");

        Application frame = Application.getInstance();

        ToolPanel toolPanel = frame.getToolPanel();
        toolPanel.setLastPickedBorderWidth(width);
        toolPanel.setBorderWidth();

        frame.repaint();
    }
}
