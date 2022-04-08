package com.zahaand.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {
    private static String indent = "___";

    public static void main(String[] args) {
        File directory = new File("out");

        printDirectoryDeeply(directory);
        printDirectoryDeeply(directory, "");
        printDirectoryDeeply2(directory, "");

    }

    // recursive call
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
        indent = indent.substring(0, (indent.length() - 3));
    }

    // recursive call #2
    public static void printDirectoryDeeply(File directory, String offset) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(offset + "F: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(offset + "D: " + file.getName());
                    printDirectoryDeeply(file, offset + "  ");
                }
            }
        }
    }

    // recursive call #3
    public static void printDirectoryDeeply2(File directory, String offset) {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                System.out.println(offset + file.getName());
                printDirectoryDeeply2(file, offset + "   ");
            }
        }
    }
}