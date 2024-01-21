package analyzer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String inputFile = args[0];
        String patternFile = args[1];
        String outputString = args[2];

        try {

            String allBytes = Files.readString(Paths.get(inputFile));

            if (allBytes.contains(patternFile)) {
                System.out.println(outputString);
            } else {
                System.out.println("Unknown file type");
            }

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
