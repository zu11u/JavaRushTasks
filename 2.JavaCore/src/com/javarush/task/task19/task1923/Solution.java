package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        while (reader.ready()) {
            boolean hasDigit = false;
            boolean isRun = true;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (isRun) {
                int val = reader.read();
                if (val >= 48 && val <= 57)
                    hasDigit = true;
                if (val != 32 && val != 10)
                    byteArrayOutputStream.write(val);
                else
                    isRun = false;
            }
            if (hasDigit)
                writer.write(byteArrayOutputStream.toString() + " ");
        }
        reader.close();
        writer.close();
    }
}
