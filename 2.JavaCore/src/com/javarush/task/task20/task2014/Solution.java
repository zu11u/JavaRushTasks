package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //System.out.println(new Solution(4));

        FileOutputStream fos = new FileOutputStream("save.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Solution savedObject = new Solution(4);
        oos.writeObject(savedObject);
        fos.close();
        oos.close();

        FileInputStream fis = new FileInputStream("save.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Solution loadedObject = (Solution) ois.readObject();
        fis.close();
        ois.close();

        System.out.println(savedObject.string.equals(loadedObject.string));
    }

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
