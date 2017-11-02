package com.javarush.task.task20.task2015;

import java.io.*;
import java.util.Random;

/* 
Переопределение сериализации
*/
public class Solution implements Serializable, Runnable {
    private transient Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        // do something here, does not matter
        Random rand = new Random();
        System.out.print(speed + " : ");
        speed += rand.nextInt(5);
        System.out.println(speed);
    }

    /**
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        runner = new Thread(this);
        runner.start();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution sol = new Solution(200);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/media/Data/TEMP/20/save.ser"));
        oos.writeObject(sol);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/media/Data/TEMP/20/save.ser"));
        Solution sol2 = (Solution) ois.readObject();
        ois.close();
    }
}
