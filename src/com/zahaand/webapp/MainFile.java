package com.zahaand.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File directory = new File("/Users/zahaand/Java/basejava/src/com/zahaand/webapp");
        if (directory.isDirectory()) {
            System.out.println("ROOT DIRECTORY " + directory.getName());
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("___" + file.getName());
                        File[] files2 = file.listFiles();
                        if (files2 != null) {
                            for (File file2 : files2) {
                                System.out.println("______" + file2.getName());
                            }
                        }
                    } else if (file.isFile()) {
                        System.out.println("___" + file.getName());
                    }
                }
            }
        }
    }
}
