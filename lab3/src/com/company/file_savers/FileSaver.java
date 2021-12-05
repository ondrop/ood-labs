package com.company.file_savers;

public class FileSaver {

    private SaveFileBehavior saveFileBehavior;

    public FileSaver() {
        this.saveFileBehavior = new SaveBinaryFile();
    }

    public FileSaver setSaveFileBehavior(SaveFileBehavior saveFileBehavior) {
        this.saveFileBehavior = saveFileBehavior;

        return this;
    }

    public void save() {
        this.saveFileBehavior.save();
    }
}
