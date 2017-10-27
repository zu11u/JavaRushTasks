package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String tag = args[0];
//        String tag = "span"; // to be removed
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
//        BufferedReader fileReader = new BufferedReader(new FileReader("/media/Data/TEMP/xml")); // to be removed
        StringBuilder stringBuilder = new StringBuilder();
        while(fileReader.ready()) {
            stringBuilder.append(fileReader.readLine());
        }
        fileReader.close();
        String fileContent = stringBuilder.toString();

        String regexStartTag = "<" + tag;
        String regexEndTag = "</" + tag + ">";
        ArrayList<Integer> startTagNum = new ArrayList<>();
        ArrayList<Integer> endTagNum = new ArrayList<>();

        Pattern startTagPattern = Pattern.compile(regexStartTag);
        Matcher startTagMatcher = startTagPattern.matcher(fileContent);
        while (startTagMatcher.find()) {
            startTagNum.add(startTagMatcher.start());
        }

        Pattern endTagPattern = Pattern.compile(regexEndTag);
        Matcher endTagMatcher = endTagPattern.matcher(fileContent);
        while (endTagMatcher.find()) {
            endTagNum.add(endTagMatcher.end());
        }

        TreeMap<Integer, String> tagContaint = new TreeMap<>();

        for (int i = 0; i < endTagNum.size();) {
            int endTagIndex = endTagNum.get(i);
            int startTagIndex = startTagNum.get(startTagNum.size()-1);
            int j = 0;
            for (j = 0; j < startTagNum.size() -1; j++) {
                if (endTagIndex > startTagNum.get(j) && endTagIndex <= startTagNum.get(j+1)) {
                    startTagIndex = startTagNum.get(j);
                    break;
                }
            }
            tagContaint.put(startTagIndex, fileContent.substring(startTagIndex, endTagIndex));
            startTagNum.remove(j);
            endTagNum.remove(i);
        }

        for (Map.Entry<Integer, String> tree : tagContaint.entrySet()) {
            System.out.println(tree.getValue());
        }
    }
}
