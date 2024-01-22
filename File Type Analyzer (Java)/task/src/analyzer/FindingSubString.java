package analyzer;

public class FindingSubString {
    final private StringMatching stringMatching;

    FindingSubString(StringMatching stringMatching) {
        this.stringMatching = stringMatching;
    }

    public int getIndexSubString(String fullText, String subString) {
        return this.stringMatching.getIndex(fullText, subString);
    }
}
