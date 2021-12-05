package com.company.file_savers;

import com.company.Application;
import org.json.JSONArray;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class SaveBinaryFile implements SaveFileBehavior {

    final String FILE_TYPE = "bin";

    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary Files", FILE_TYPE);

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
                FileOutputStream fileWriter = new FileOutputStream(fileToSave);
                JSONArray state = Application.getInstance().getAppState();

                byte[] bytes = state.toString().getBytes(StandardCharsets.UTF_8);
                fileWriter.write(bytes);
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
