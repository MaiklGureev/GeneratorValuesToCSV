package com.company;

import java.util.Random;
import java.util.stream.Stream;

public class Generator {

    int countExperiments;
    boolean isTheftInPorch;
    int totalCount;

    int countPorch;
    int countTypeApartmentOne;
    int countTypeApartmentTwo;
    int countTypeApartmentThree;
    int countTheftInPorch;

    int valuePorch;
    int valueTypeApartmentOne;
    int valueTypeApartmentTwo;
    int valueTypeApartmentThree;
    int valueTheftInPorch;

    int dispersionPorch;
    int dispersionTypeOne;
    int dispersionTypeTwo;
    int dispersionTypeThree;
    int dispersionTheftValueInPorch;

    public Generator() {

    }

    public void init() {
        totalCount = countTypeApartmentOne + countTypeApartmentTwo + countTypeApartmentThree + 2;
    }

    public int[][] doExperiment() {
        init();

        int[][] values = new int[totalCount][7];

        for (int a = 0; a < countTypeApartmentOne; a++) {
            for (int b = 0; b < 7; b++) {
                int v = getRandomValue(valueTypeApartmentOne, dispersionTypeOne);
                values[a][b] = v;
            }
        }

        for (int a = countTypeApartmentOne; a < (countTypeApartmentOne + countTypeApartmentTwo); a++) {
            for (int b = 0; b < 7; b++) {
                int v = getRandomValue(valueTypeApartmentTwo, dispersionTypeTwo);
                values[a][b] = v;
            }
        }

        for (int a = countTypeApartmentOne + countTypeApartmentTwo; a < (countTypeApartmentOne + countTypeApartmentTwo + countTypeApartmentThree); a++) {
            for (int b = 0; b < 7; b++) {
                int v = getRandomValue(valueTypeApartmentThree, dispersionTypeThree);
                values[a][b] = v;
            }
        }

        for (int b = 0; b < 7; b++) {
            values[totalCount - 2][b] = getRandomValue(valuePorch, dispersionPorch);
            for (int a = 0; a < totalCount-2; a++) {
                values[totalCount - 2][b] += values[a][b];
            }
            if(isTheftInPorch){
                for(int c = 0; c<countTheftInPorch;c++){
                    values[totalCount - 2][b] += getRandomValue(valueTheftInPorch, dispersionTheftValueInPorch);
                }
            }
        }
        return values;
    }

    public String[] doExperiments(){
        String [] tempArray;
        String [] totalArray = new String[]{};
        for(int a= 0; a<countExperiments;a++){
            tempArray=convertIntMatrixToStringArray(doExperiment());
            totalArray = combine(tempArray,totalArray);
        }
        return totalArray;
    }

    public int getRandomValue(int value, int dispersion) {
        int a = value - value * dispersion / 100;
        int b = value + value * dispersion / 100;
        Random random = new Random();
        int random_num = a + (int) (random.nextGaussian() * (b - a));

        while(random_num < a | random_num > b ){
            random_num = getRandomValue(value,dispersion);
        }
        return random_num;
    }

    public String[] convertIntMatrixToStringArray(int[][] values){
        String [] array = new String[totalCount];
        for (int a = 0; a < totalCount; a++) {
            array[a]="";
            for (int b = 0; b < 7; b++) {
                array[a]+=values[a][b];
                if(b!=6){ array[a]+=","; }
            }
        }
        return array;
    }

    public static String[] combine(String[] a, String[] b){
        int length = a.length + b.length;
        String[] result = new String[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }


}
