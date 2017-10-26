package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(args[1]));
        StringBuilder sb = new StringBuilder();
        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] lineSplit = line.split("\\s");
            for (String s : lineSplit) {
                if (s.trim().length() > 6) {
                    sb.append(s.trim()).append(",");
                }
            }
//
//            Pattern pattern = Pattern.compile("(?<=\\s|^)\\w{7,}(?=\\s|$)");
//            Matcher matcher = pattern.matcher(line);
//            while (matcher.find()) {
//                sb.append(line.substring(matcher.start(),matcher.end())).append(",");
//            }
        }
        String writeLine = sb.deleteCharAt(sb.length()-1).toString();
        fileWriter.write(writeLine);

        fileWriter.close();
        fileReader.close();
    }
}
