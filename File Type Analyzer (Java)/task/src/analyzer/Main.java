package analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        List<PatternFile> listOfPattern = new ArrayList<>();

        String rootFolder = args[0];
        String dbPattern = args[1];

        try {
            Scanner scanner = new Scanner(new File(dbPattern));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replaceAll("\"", "");
                String[] splitLine = line.split(";");

                listOfPattern.add(new PatternFile(Integer.parseInt(splitLine[0]), splitLine[1], splitLine[2]));
            }

            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }

        listOfPattern.sort(Collections.reverseOrder());


        List<Callable<String>> callables = new ArrayList<>();

        FindingSubString findingSubString = new FindingSubString(new KMPAlgorithm());

        File folder = new File(rootFolder);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            ExecutorService executor = Executors.newFixedThreadPool(listOfFiles.length);
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Callable<String> callable = () -> findingSubString.checkFileType(rootFolder, file.getName(), listOfPattern);
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
