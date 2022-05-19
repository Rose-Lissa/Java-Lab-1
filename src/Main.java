import csvstatistics.Analyzer;
import csvstatistics.CSVRecord;
import csvstatistics.WordAnalyzer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class Main {
    public static void main(String args[]){
        final Analyzer fileWordAnalyzer;
        try(FileReader reader = new FileReader("in.txt");
            PrintWriter writer = new PrintWriter(new FileWriter("out.csv"), true)){
            fileWordAnalyzer = new WordAnalyzer(reader);
            Set<CSVRecord> csvRecords = fileWordAnalyzer.collectStatistics();
            for(CSVRecord record : csvRecords){
                writer.print(record);
            }
        } catch (IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
    }
}
