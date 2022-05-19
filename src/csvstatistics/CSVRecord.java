package csvstatistics;

import java.util.List;

public interface CSVRecord extends Comparable<CSVRecord> {
    public static final Character DEFAULT_COLUMN_SEPARATOR = ',';
    public static final Character DEFAULT_ROW_SEPARATOR = '\n';
    public static final Character DEFAULT_ESCAPE_CHARACTER = '\"';

    List<Object> getRecord();
}
