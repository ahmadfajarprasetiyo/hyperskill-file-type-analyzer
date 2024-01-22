package analyzer;

public class NaiveAlgorithm implements StringMatching {
    public int getIndex(String fullText, String subString) {
        int res = -1;
        int M = subString.length();
        int N = fullText.length();

        int j = 0;
        int i = 0;
        int firstMatch = 0;
        while (i < N) {
            if (subString.charAt(j) == fullText.charAt(i)) {
                if (j == 0) {
                    firstMatch = i;
                }
                j++;
                i++;
            }

            if (j == M) {
                res = i - j;
                break;
            } else if (i < N && subString.charAt(j) != fullText.charAt(i)) {
                if (j != 0) {
                    i = firstMatch + 1;
                    j = 0;
                } else {
                    i = i + 1;
                }

            }
        }

        return res;
    }
}
