package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    //Статический метод для считывания файла и вывода на консоль принимает путь к файлу
    public static void readFile(String path)
            throws FileNotFoundException { //Это значит что внутри метода может произойти усключение (Exception как Иванцова объясняла)
        //Создаём файл, передаём путь
        File myFile = new File(path);
        //Создаём сканер передаём ему файл
        Scanner myReader = new Scanner(myFile);
        //Выводим имя файла который будет выведен
        System.out.println("File name: " + path);
        //Цикл пока в файле есть следующщая строка
        while (myReader.hasNextLine()) {
            //Считываем следющую строку и переходим на неё
            String data = myReader.nextLine();
            //Выводим в консоль
            System.out.println(data);
        }
        myReader.close();
    }

    public static void main(String[] args) throws IOException {

        //Создаём новый поток. -> это лямбда выражение.
        Thread t1 = new Thread(() -> {
            //Передаём потоку, что когда мы выозовем метод start сделай это:
            try {
                //Попробуй выполнить метод
                readFile("File1.txt");
            } catch (FileNotFoundException e) {
                //Если возникла ошибка FileNotFoundException то выведи текст ошибки в консоль
                e.printStackTrace();
            }
        });

        //Тоже самое
        Thread t2 = new Thread(() -> {
            try {
                readFile("File2.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        //Тоже самое
        Thread t3 = new Thread(() -> {
            try {
                readFile("File3.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        //Вызываем метод start у всех потоков:
        t1.start();
        t2.start();
        t3.start();
    }
}
