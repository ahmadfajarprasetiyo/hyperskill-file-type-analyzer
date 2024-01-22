package analyzer;

import java.io.IOException;
import java.lang.module.FindException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        final String NAIVE_ALGORITHM = "--naive";
        final String KMP_ALGORITHM = "--KMP";

        String algorithm = args[0];
        String inputFile = args[1];
        String patternFile = args[2];
        String outputString = args[3];


        FindingSubString findingSubString;

        if (algorithm.equals(NAIVE_ALGORITHM)) {
            findingSubString = new FindingSubString(new NaiveAlgorithm());
        } else {
            findingSubString = new FindingSubString(new KMPAlgorithm());
        }



        try {
            long startTime = System.nanoTime();

            String allBytes = Files.readString(Paths.get(inputFile));
            System.out.println(allBytes);

            if (findingSubString.getIndexSubString(allBytes, patternFile) != -1) {
                System.out.println(outputString);
            } else {
                System.out.println("Unknown file type");
            }

            long elapsedNanos = System.nanoTime() - startTime;

            System.out.printf("It took %f seconds", elapsedNanos/1000000000.0);


        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
