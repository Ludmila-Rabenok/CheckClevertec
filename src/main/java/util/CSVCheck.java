package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVCheck {

    public List<String> readAll(String fileName) {
        String str;
        List<String> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(
                "c:\\Users\\User\\IdeaProjects\\checkClevertec\\src\\main\\resources\\" + fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((str = bufferedReader.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            System.err.println("Error during import data from csv file ");
            e.getStackTrace();
        }
        return list;
    }

    public boolean writeAll(List<String> list) {
        try (FileWriter fw = new FileWriter(
                "c:\\Users\\User\\IdeaProjects\\checkClevertec\\src\\main\\resources\\checkExport.csv")) {
            for (String str : list) {
                fw.write(str);
                fw.flush();
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error during export data to csv file ");
            e.getStackTrace();
        }
        return false;
    }

    public List<String> prepareForExportCSV(List<String> list) {
        return list.stream().map(s -> s + "\n").collect(Collectors.toList());
    }

}
