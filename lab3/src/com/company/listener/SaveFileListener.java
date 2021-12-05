package com.company.listener;

import com.company.file_savers.FileSaver;
import com.company.file_savers.SaveFileBehavior;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFileListener implements ActionListener {

    private SaveFileBehavior saveFileBehavior;
    private FileSaver fileSaver = new FileSaver();

    public SaveFileListener(String saveFileBehaviorName) {
        try {
            this.saveFileBehavior = (SaveFileBehavior) Class.forName(saveFileBehaviorName).getConstructor().newInstance();
            this.fileSaver.setSaveFileBehavior(saveFileBehavior);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.fileSaver.save();
    }
}
