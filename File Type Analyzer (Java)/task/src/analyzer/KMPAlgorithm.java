package analyzer;

public class KMPAlgorithm implements StringMatching {
    public int getIndex(String fullText, String subString) {
        int res = -1;
        int M = subString.length();
        int N = fullText.length();

        int[] lps = new int[M];

        computeLPSArray(subString, M, lps);

        int j = 0;
        int i = 0;
        while (i < N) {
            if (subString.charAt(j) == fullText.charAt(i)) {
                j++;
                i++;
            }

            if (j == M) {
                res = i - j;
                break;
            } else if (i < N && subString.charAt(j) != fullText.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }

            }
        }

        return res;
    }

    private void computeLPSArray(String pat, int M, int lps[]) {

        int len = 0;
        int i = 1;
        lps[0] = 0;


        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
}
