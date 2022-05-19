package csvstatistics;

import java.util.ArrayList;
import java.util.List;

public class CSVWordStatisticsRecord implements CSVRecord, Comparable<CSVRecord> {
    public CSVWordStatisticsRecord(String word){
        this.word = word;
        numInclusions = 1;
        ratio = 0.0;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getNumInclusions() {
        return numInclusions;
    }

    public void addInclude(){
        numInclusions++;
    }

    public void countRatio(int numAllWords){
        ratio = (double) numInclusions / numAllWords;
    }

    public String getWord() {
        return word;
    }

    @Override
    public List<Object> getRecord() {
        List<Object> record = new ArrayList<>();
        record.add(word);
        record.add(numInclusions);
        record.add(ratio);
        return record;
    }

    @Override
    public int compareTo(CSVRecord other) {
        int compareNumInclusions =  ((CSVWordStatisticsRecord)other).numInclusions - numInclusions;
        int compareWords = word.compareTo(((CSVWordStatisticsRecord) other).word);
        return compareNumInclusions != 0? compareNumInclusions : compareWords;
    }

    @Override
    public String toString() {
        return escapingCharacter + word + escapingCharacter +  columnSeparator + " "
                + numInclusions + columnSeparator + " "
                + ratio + rowSeparator;
    }

    private String word;
    private Integer numInclusions;
    private Double ratio;

    private Character rowSeparator = DEFAULT_ROW_SEPARATOR;
    private Character columnSeparator = DEFAULT_COLUMN_SEPARATOR;
    private Character escapingCharacter = DEFAULT_ESCAPE_CHARACTER;
}
