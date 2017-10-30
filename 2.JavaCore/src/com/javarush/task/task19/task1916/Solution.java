package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName = reader.readLine();
        String secondFileName = reader.readLine();
        reader.close();
        BufferedReader reader1 = new BufferedReader(new FileReader(firstFileName));
        BufferedReader reader2 = new BufferedReader(new FileReader(secondFileName));

//        BufferedReader reader1 = new BufferedReader(new FileReader("/media/Data/TEMP/file1"));
//        BufferedReader reader2 = new BufferedReader(new FileReader("/media/Data/TEMP/file2"));

        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();

        while (reader1.ready()) {
            arrayList1.add(reader1.readLine());
        }
        reader1.close();

        while (reader2.ready()) {
            arrayList2.add(reader2.readLine());
        }
        reader2.close();

        int len1 = arrayList1.size();
        int len2 = arrayList2.size();

        int j = 0;
        for (int i = 0; i < len1; ) {
            String line1 = arrayList1.get(i);
            String line2 = "";
            line2 = j < len2 ? arrayList2.get(j) : "";

            if (line1.equals(line2)) {
                lines.add(new LineItem(Type.SAME, line1));
//                System.out.println("same " + line1);
                i++;
                j++;
            } else if (j+1 < len2 && line1.equals(arrayList2.get(j+1))) {
                lines.add(new LineItem(Type.ADDED, line2));
//                System.out.println("added " + line2);
                j++;
            } else {
                lines.add(new LineItem(Type.REMOVED, line1));
//                System.out.println("removed " + line1);
                i++;
            }
        }

        if (j < len2) {
            lines.add(new LineItem(Type.ADDED, arrayList2.get(j)));
//            System.out.println("added " + arrayList2.get(j));
        }

    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
