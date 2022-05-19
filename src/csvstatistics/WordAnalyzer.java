package csvstatistics;

import com.sun.source.tree.Tree;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class WordAnalyzer implements Analyzer{
    public WordAnalyzer(Reader reader){
        this.reader = reader;
    }

    @Override
    public Set<CSVRecord> collectStatistics() throws IOException {
        Map<String, CSVWordStatisticsRecord> statistics = new TreeMap<>();
        parseText(statistics);
        return countRatioAndConvertToList(statistics);
    }

    private void parseText(Map<String, CSVWordStatisticsRecord> statistics) throws IOException{
        StringBuilder word = new StringBuilder();
        char[] buffer = new char[1000];
        int numCharRead;
        while ((numCharRead = reader.read(buffer)) != -1){
            for(int i = 0; i < numCharRead; i++){
                if(Character.isLetterOrDigit(buffer[i])){
                    word.append(buffer[i]);
                }
                else if(!word.isEmpty()){
                    CSVWordStatisticsRecord record = statistics.putIfAbsent(word.toString(), new CSVWordStatisticsRecord(word.toString()));
                    if(record != null) {
                        record.addInclude();
                    }
                    word = new StringBuilder();
                }
            }
        }
    }

    private Set<CSVRecord> countRatioAndConvertToList(Map<String, CSVWordStatisticsRecord> statistics){
        Set<CSVRecord> records = new TreeSet<>();
        int numAllWords = 0;
        for(CSVWordStatisticsRecord record : statistics.values()){
            numAllWords += record.getNumInclusions();
        }
        for(CSVWordStatisticsRecord record : statistics.values()){
            record.countRatio(numAllWords);
            records.add(record);
        }
        return records;
    }

    private Reader reader;
}
