package com.zahaand.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File directory = new File("src/com/zahaand/webapp");

        if (directory.isDirectory()) {
            System.out.println("ROOT DIRECTORY: " + directory.getName());
            System.out.println("PATH: " + directory.getPath());
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                System.out.println(file.getName());
                if (file.isDirectory()) {
                    for (File file2 : Objects.requireNonNull(file.listFiles())) {
                        System.out.println("___" + file2.getName());
                    }
                }
            }
        }
    }
}
