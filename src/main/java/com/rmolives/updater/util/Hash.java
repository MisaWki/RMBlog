package com.rmolives.updater.util;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Objects;

public class Hash {
    public static String hashFiles(String folderPath) throws IOException {
        StringBuilder builder = new StringBuilder();
        File directory = new File(folderPath);
        if (!directory.isDirectory())
            throw new FileNotFoundException('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                if (files[i].isDirectory())
                    builder.append(hashFiles(files[i].getAbsolutePath()));
                else {
                    FileInputStream fis = new FileInputStream(files[i]);
                    builder.append(hashCode(fis));
                    fis.close();
                }
            }
        }
        return builder.toString();
    }

    public static String hashCode(InputStream fis) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer, 0, 1024)) != -1)
                md.update(buffer, 0, length);
            fis.close();
            byte[] md5Bytes = md.digest();
            BigInteger bigInt = new BigInteger(1, md5Bytes);
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
