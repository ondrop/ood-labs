package com.company.file_reader;

import org.json.JSONArray;

import java.io.IOException;

public abstract class AbstractFileReader {

    public final JSONArray getFileData(String path) {
        String stringData = null;
        try {
            stringData = readData(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseData(stringData);
    }

    abstract String readData(String path) throws IOException;

    public JSONArray parseData(String stringData) {
        return new JSONArray(stringData);
    }
}
