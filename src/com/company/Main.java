package com.company;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here

        Generator generator = new Generator();
        Scanner in = new Scanner(System.in);

        System.out.println("Количество экспериментов");
        generator.countExperiments = in.nextInt();

        System.out.println("Параметры первого типа квартиры: количество квартир, средний расход в сутки (кВт*ч), разброс расхода в процентах (0-100)");

        generator.countTypeApartmentOne = in.nextInt();
        generator.valueTypeApartmentOne = in.nextInt();
        generator.dispersionTypeOne = in.nextInt();

        System.out.println("Параметры второго типа квартиры: количество квартир, средний расход в сутки (кВт*ч), разброс расхода в процентах (0-100)");
        generator.countTypeApartmentTwo = in.nextInt();
        generator.valueTypeApartmentTwo = in.nextInt();
        generator.dispersionTypeTwo = in.nextInt();

        System.out.println("Параметры третьего типа квартиры: количество квартир, средний расход в сутки (кВт*ч), разброс расхода в процентах (0-100)");
        generator.countTypeApartmentThree = in.nextInt();
        generator.valueTypeApartmentThree = in.nextInt();
        generator.dispersionTypeThree = in.nextInt();

        System.out.println("Параметры подъезда: количество подъездов, средний расход в сутки (кВт*ч), разброс расхода в процентах (0-100)");
        generator.countPorch = in.nextInt();
        generator.valuePorch = in.nextInt();
        generator.dispersionPorch = in.nextInt();

        addTheftDevice(in,generator);

        String fileName = addFileName();

        Runnable task = () -> {
            System.out.println("Выполнение генерации...");
            WriterToCSV.createCSV(generator.doExperiments(), fileName);
            System.out.println("Готово!");
        };
        Thread thread = new Thread(task);
        thread.start();

        in.close();





    }

    static String addFileName(){
        Date date = new Date();
        String fileName = date.toString();
        fileName = fileName.replace(":"," ");
        return fileName;
    }
    static void addTheftDevice(Scanner in, Generator generator){
        System.out.println("Добавить ворующий прибор? (да/нет)");
        String answer = in.nextLine();
        if (answer.equals("да")) {
            generator.isTheftInPorch = true;
            System.out.println("Выбрано да.");
            System.out.println("Параметры ворующего прибора: количество в доме, средний расход в сутки (кВт*ч), разброс расхода в процентах (0-100)");
            generator.countTheftInPorch = in.nextInt();
            generator.valueTheftInPorch = in.nextInt();
            generator.dispersionTheftValueInPorch = in.nextInt();
        } else if (answer.equals("нет")) {
            generator.isTheftInPorch = false;
            System.out.println("Выбрано нет.");
        } else {
            System.out.println("Ошибка ввода. Повторите.");
            addTheftDevice(in,generator);
        }
    }


}
