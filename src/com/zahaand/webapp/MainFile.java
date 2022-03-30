package com.zahaand.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File directory = new File("src/com/zahaand/webapp");
        MainFile mainFile = new MainFile();

        mainFile.printAllFilesNames(directory);

    }

    private void printAllFilesNames(File directory) {
        if (directory.isFile()) {
            System.out.println(directory.getName());
        } else if (directory.isDirectory()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                printAllFilesNames(file);
            }
        }
    }
}