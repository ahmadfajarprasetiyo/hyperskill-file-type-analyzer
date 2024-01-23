package analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        String rootFolder = args[0];
        String patternFile = args[1];
        String outputString = args[2];

        List<Callable<String>> callables = new ArrayList<>();

        FindingSubString findingSubString = new FindingSubString(new KMPAlgorithm());

        File folder = new File(rootFolder);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            ExecutorService executor = Executors.newFixedThreadPool(listOfFiles.length);
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Callable<String> callable = () -> findingSubString.checkFileType(rootFolder, file.getName(), patternFile, outputString);
                    callables.add(callable);
                }
            }

            try {
                List<Future<String>> futures = executor.invokeAll(callables);
                for (Future<String> future : futures) {
                    System.out.println(future.get());
                }
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(ex.toString());
            }

        }


    }
}
