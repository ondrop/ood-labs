package com.company;

public class Main {

    public static void main(String[] args) {
        try {
            Application application = Application.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
