package com.BakeryOrder.util;

import java.io.*;

public class FileUtil {
    public static void ensureDataFileExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                // Initialize with empty list if it's a serialized file
                if (filePath.endsWith(".ser")) {
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                        oos.writeObject(new java.util.ArrayList<>());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
