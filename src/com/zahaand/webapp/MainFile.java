package com.zahaand.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File directory = new File("out");

        printDirectoryDeeply(directory);

    }

    private static void printDirectoryDeeply(File directory) {
        if (directory.isDirectory()) {
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println(stringBuilder + directory.getName());
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        stringBuilder.append("___");
                        printDirectoryDeeply(file);
                    }
                }
            }
        }
    }
}