package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FindingSubString {
    final private StringMatching stringMatching;

    FindingSubString(StringMatching stringMatching) {
        this.stringMatching = stringMatching;
    }

    public int getIndexSubString(String fullText, String subString) {
        return this.stringMatching.getIndex(fullText, subString);
    }

    public String checkFileType(String folderPath, String nameFile, List<PatternFile> patternFileList) {
        String res = nameFile + ": Unknown file type";
        try {
            String allBytes = Files.readString(Paths.get(folderPath+"/"+nameFile));

            for (PatternFile patternFile : patternFileList) {
                if (this.getIndexSubString(allBytes, patternFile.getPattern()) != -1) {
                    res = nameFile + ": " + patternFile.getFileType();
                    break;
                }
            }


        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        return res;
    }

}
