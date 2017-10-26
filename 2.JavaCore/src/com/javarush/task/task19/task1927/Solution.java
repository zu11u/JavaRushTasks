package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream origPrint = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newPrint = new PrintStream(outputStream);
        System.setOut(newPrint);

        testString.printSomething();
        System.setOut(origPrint);
        String line = outputStream.toString();
        String[] lineSplit = line.split("\\n");
        int count = 0;
        for (String s : lineSplit) {
            System.out.println(s);
            count++;
            if (count == 2) {
                System.out.println("JavaRush - курсы Java онлайн");
                count = 0;
            }
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
