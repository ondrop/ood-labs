package com.company.listener;

import com.company.Application;
import com.company.file_reader.AbstractFileReader;
import com.company.shape.ShapeCompound;
import org.json.JSONArray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OpenFileListener implements ActionListener {

    private AbstractFileReader fileReader;

    public OpenFileListener(String openFileBehaviorName) {
        try {
            this.fileReader = (AbstractFileReader) Class.forName(openFileBehaviorName).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog fileDialog = new FileDialog(Application.getInstance(), "Choose a file", FileDialog.LOAD);
        fileDialog.setFile("*.bin;*.txt");
        fileDialog.setVisible(true);
        String filename = fileDialog.getDirectory() + fileDialog.getFile();
        if (filename != null) {
            JSONArray data = this.fileReader.getFileData(filename);
            ArrayList<ShapeCompound> newShapes = Application.parseStateShapes(data);
            Application.getInstance().setShapes(newShapes);
            Application.getInstance().repaint();
        }
    }
}
