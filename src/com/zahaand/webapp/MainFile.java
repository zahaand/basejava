package com.zahaand.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File directory = new File("src/com/zahaand/webapp");

        printDirectoryDeeply(directory);

    }

    private static void printDirectoryDeeply(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            assert files != null;
            System.out.println(directory.getName());
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("___" + file.getName());
                }
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    printDirectoryDeeply(file);
                }
            }
        }
    }
}