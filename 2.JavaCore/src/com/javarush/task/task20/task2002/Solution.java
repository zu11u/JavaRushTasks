package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream("/media/Data/TEMP/20/javarush_save");
            InputStream inputStream = new FileInputStream("/media/Data/TEMP/20/javarush_save");

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            User user1 = new User();
            user1.setFirstName("user1_name");
            user1.setLastName("user1_lastname");
            user1.setMale(true);
            user1.setBirthDate(new Date(60616576800000L));
            user1.setCountry(User.Country.RUSSIA);

            User user2 = new User();
            user2.setLastName("user2_lastname");
            user2.setMale(false);
            user2.setBirthDate(new Date(60774256800000L));
            user2.setCountry(User.Country.UKRAINE);

            User user3 = new User();
            user3.setFirstName("user3_name");
            user3.setMale(true);
            user3.setBirthDate(new Date(60616576800000L));
            user3.setCountry(User.Country.RUSSIA);

            User user4 = new User();
            user4.setFirstName("user4_name");
            user4.setLastName("user4_lastname");
            user4.setMale(true);
            user4.setCountry(User.Country.RUSSIA);

            User user5 = new User();
            user5.setFirstName("user5_name");
            user5.setLastName("user5_lastname");
            user5.setMale(true);
            user5.setBirthDate(new Date(60616576800000L));

            User user6 = new User();
            user6.setFirstName("user1_name");
            user6.setLastName("user1_lastname");
            user6.setBirthDate(new Date(60616576800000L));
            user6.setCountry(User.Country.RUSSIA);

            javaRush.users.add(user1);
            javaRush.users.add(user2);
            javaRush.users.add(user3);
            javaRush.users.add(user4);
            javaRush.users.add(user5);
            javaRush.users.add(user6);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
//            check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            System.out.println(javaRush.equals(loadedObject));

            for (int i = 0; i < javaRush.users.size(); i++) {
                if (javaRush.users.get(i).equals(loadedObject.users.get(i))) {
                    System.out.println("True");
                } else {
                    System.out.println("false");
                }
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);
            if (users.size() > 0) {
                for (User u : users) {
                    printWriter.println(u.getFirstName() == null ? "null" : u.getFirstName());
                    printWriter.println(u.getLastName() == null ? "null" : u.getLastName());
                    Date d = u.getBirthDate();
                    printWriter.println(u.getBirthDate() == null ? "null" : u.getBirthDate().getTime());
                    printWriter.println(u.getCountry() == null ? "null" : u.getCountry().getDisplayedName());
                    printWriter.println(u.isMale());
                }
            } else {
                printWriter.print("");
            }
            printWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (bufferedReader.ready()) {
                User user = new User();
                String temp = bufferedReader.readLine();
                user.setFirstName(temp.equals("null") ? null : temp);
                temp = bufferedReader.readLine();
                user.setLastName(temp.equals("null") ? null : temp);
                temp = bufferedReader.readLine();
                user.setBirthDate(temp.equals("null") ? null : new Date(Long.parseLong(temp)));
                temp = bufferedReader.readLine();
                switch (temp) {
                    case "Russia":
                        user.setCountry(User.Country.RUSSIA);
                        break;
                    case "Ukraine":
                        user.setCountry(User.Country.UKRAINE);
                        break;
                    case "Other":
                        user.setCountry(User.Country.OTHER);
                }
                user.setMale(Boolean.parseBoolean(bufferedReader.readLine()));
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
