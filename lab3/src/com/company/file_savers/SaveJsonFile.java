package com.company.file_savers;

import com.company.Application;
import org.json.JSONArray;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class SaveJsonFile implements SaveFileBehavior {

    final String FILE_TYPE = "json";

    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("Json Files", FILE_TYPE);

    @Override
    public void save() {
        FileDialog fileDialog = new FileDialog(Application.getInstance(), "Save file", FileDialog.SAVE);
        fileDialog.setFile("shape." + FILE_TYPE);
        fileDialog.setVisible(true);
        String fileName = fileDialog.getFile();
        if (fileName != null) {
            if (!fileName.endsWith("." + FILE_TYPE)) {
                fileName = fileName + "." + FILE_TYPE;
            }

            File fileToSave = new File(fileDialog.getDirectory(), fileName);
            try {
                FileWriter fileWriter = new FileWriter(fileToSave);
                JSONArray state = Application.getInstance().getAppState();
                fileWriter.write(state.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
