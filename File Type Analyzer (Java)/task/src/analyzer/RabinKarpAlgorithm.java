package analyzer;

public class RabinKarpAlgorithm implements StringMatching {

    final private int factor = 37;
    final private int mod = 97;


    public int getIndex(String fullText, String subString) {
        int res = -1;
        int M = subString.length();
        int N = fullText.length();

        if (M > N) {
            return res;
        }

        long hashPattern = getHashValue(subString);
        long hashText = getHashValue(fullText.substring(N-M));
        long lastMod = getModPow(this.factor, M, this.mod);

        int pointerRight = N - 1;
        int pointerLeft = N - 1 - M;
        while (pointerLeft >= 0) {
            if (hashPattern == hashText && subString.equals(fullText.substring(pointerLeft + 1, pointerRight + 1))) {
                res = pointerLeft;
                break;
            }

            hashText = (hashText - ((lastMod*fullText.charAt(pointerRight)) % this.mod) + this.mod) % this.mod;
            hashText = (hashText * this.factor + fullText.charAt(pointerLeft)) % this.mod;

            pointerRight--;
            pointerLeft--;
        }

        return res;
    }

    private long getHashValue(String text) {
        long hashValue = 0;
        for (int i = text.length() - 1; i > 0; i--) {
            hashValue = ((text.charAt(i) + hashValue) * factor) % mod;
        }

        hashValue = (hashValue + text.charAt(0)) % mod;

        return hashValue;
    }

    private long getModPow(long base, long pow, long mod) {
        long res = base;

        for (int i = 2; i < pow; i++) {
            res = (res * base) % mod;
        }

        return res;
    }
}
