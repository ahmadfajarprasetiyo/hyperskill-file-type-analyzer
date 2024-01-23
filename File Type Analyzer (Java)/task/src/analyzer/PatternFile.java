package analyzer;

public class PatternFile implements Comparable<PatternFile> {
    private final int priority;
    private final String pattern;
    private final String fileType;

    PatternFile(int priority, String pattern, String fileType) {
        this.priority = priority;
        this.pattern = pattern;
        this.fileType = fileType;
    }

    public String getPattern() {
        return pattern;
    }

    public String getFileType() {
        return fileType;
    }

    @Override
    public int compareTo(PatternFile other) {
        // Compare books based on their publication year
        return Integer.compare(this.priority, other.priority);
    }


}
