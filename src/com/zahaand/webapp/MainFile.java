package com.zahaand.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {
    static String indent = "___";

    public static void main(String[] args) {
        File directory = new File("out");

        printDirectoryDeeply(directory);

    }

    private static void printDirectoryDeeply(File directory) {
        if (directory.isDirectory()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                System.out.println(indent + file.getName());
                if (file.isDirectory()) {
                    indent += "___";
                    printDirectoryDeeply(file);
                }
            }
        }
        indent = "___";
    }
}