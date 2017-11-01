package com.javarush.task.task20.task2009;

import java.io.FileInputStream;
import java.io.Serializable;

/*
Как сериализовать static?
*/
public class Solution {
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "it's test static string";
        public int i;
        public int j;

        public static String getStaticString() {
            return staticString;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public static void setStaticString(String staticString) {
            ClassWithStatic.staticString = staticString;
        }

        public void setI(int i) {
            this.i = i;
        }

        public void setJ(int j) {
            this.j = j;
        }
    }

    public static void main(String[] args) {

    }
}
