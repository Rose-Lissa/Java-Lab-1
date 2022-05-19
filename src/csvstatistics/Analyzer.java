package csvstatistics;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface Analyzer {
    Set<CSVRecord> collectStatistics() throws IOException;
}
