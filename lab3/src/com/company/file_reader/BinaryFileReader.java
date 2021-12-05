package com.company.file_reader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class BinaryFileReader extends AbstractFileReader {

    @Override
    String readData(String path) throws IOException {
        File file = new File(path);
        String text = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8).toString();
        return text.substring(1, text.length()-1);
    }
}
