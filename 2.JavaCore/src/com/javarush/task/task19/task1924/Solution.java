package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        fileName = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

        while (fileReader.ready()) {
            String[] lineSplit = fileReader.readLine().split("\\s");
            StringBuilder sb = new StringBuilder();
            for (String s : lineSplit) {
                int val = map.size();
                try {
                    val = Integer.parseInt(s.trim());
                } catch (NumberFormatException e) {
                }
                if (map.containsKey(val)) {
                    s = map.get(val);
                }
                sb.append(s).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
        fileReader.close();

    }
}
