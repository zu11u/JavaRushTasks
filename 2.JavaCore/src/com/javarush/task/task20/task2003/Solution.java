package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        FileInputStream fis = null;
        try {
            fileName = reader.readLine();
            reader.close();
            fis = new FileInputStream(fileName);
            load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();

        for (Map.Entry<String, String> map : properties.entrySet()) {
            prop.setProperty(map.getKey(), map.getValue());
        }
        prop.store(outputStream, "save");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.load(inputStream);
        for (String key : prop.stringPropertyNames()) {
            properties.put(key, prop.getProperty(key));
        }

    }

    public static void main(String[] args) {

    }
}
