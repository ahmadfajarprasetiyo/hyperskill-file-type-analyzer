package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FindingSubString {
    final private StringMatching stringMatching;

    FindingSubString(StringMatching stringMatching) {
        this.stringMatching = stringMatching;
    }

    public int getIndexSubString(String fullText, String subString) {
        return this.stringMatching.getIndex(fullText, subString);
    }

    public String checkFileType(String folderPath, String nameFile, String pattern, String typeFile) {
        String res = nameFile + ": Unknown file type";
        try {
            String allBytes = Files.readString(Paths.get(folderPath+"/"+nameFile));

            if (this.getIndexSubString(allBytes, pattern) != -1) {
                res = nameFile + ": " + typeFile;
            }

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        return res;
    }

}
