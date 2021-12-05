package com.company.listener;

import com.company.command.CreateShapeCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddShapeActionListener implements ActionListener {

    private CreateShapeCommand createShapeCommand;

    public AddShapeActionListener(String shapeName) {
        this.createShapeCommand = new CreateShapeCommand(shapeName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createShapeCommand.execute();
    }
}
