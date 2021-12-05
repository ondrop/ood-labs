package com.company.file_reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileReader extends AbstractFileReader {

    @Override
    String readData(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }
}
