package com.company;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import javafx.scene.input.DataFormat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WriterToCSV {


    public static void createCSV(String[] array, String nameExperiment) {
        try {

            FileWriter writer = new FileWriter(nameExperiment + ".csv");

            for (int a = 0; a < array.length; a++) {
                if (!array[a].equals("0,0,0,0,0,0,0"))
                    writer.append(array[a] + "\n");
                else
                    writer.append("\n");
            }

            //generate whatever data you want

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
