package com.zahaand.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File directory = new File("src/com/zahaand/webapp");

        printDirectoryDeeply(directory);

    }

    private static void printDirectoryDeeply(File directory) {
        if (directory.isFile()) {
            System.out.println("___" + directory.getName());
        } else if (directory.isDirectory()) {
            System.out.println(directory.getName());
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                printDirectoryDeeply(file);
            }
        }
    }
}