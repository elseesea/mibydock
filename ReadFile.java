import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    public static void main(String[] args) {

        Map wordCount = new HashMap<String, Integer>();

        // Build Stop Words ArrayList
        ArrayList<String> stopWords = new ArrayList<String>();
        try {
            File file = new File("stop-words.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (
                    data.length()>0 &&
                    !data.substring(0,1).equals("#")) {
                        stopWords.add(data);
                    }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    /* Testing successful building of Stop Words ArrayList
        for (int i=0; i<5 && i<stopWords.size(); i++) {
            System.out.println(stopWords.get(i));
        }
        System.out.print(stopWords.size());
    */


        // Read line from text
        String[] wordsInLine;
        try {
            File file = new File("mobydick.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim().toLowerCase();
                if (data.length()>0) {
                    wordsInLine = data.split("[^a-z']+");
                    for (int i=0; i<wordsInLine.length; i++) {
                        String word = wordsInLine[i];
                        if (!stopWords.contains(word) && word.length()>0) {
                            if (wordCount.containsKey(word)) {
                                Integer count = (Integer)wordCount.get(word);
                                wordCount.replace(word, count+1);
                            } else {
                                wordCount.put(word, 1);
                            }
                        }
                    }
                }
            }
            scanner.close();
            System.out.println(wordCount);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }  
    }
}