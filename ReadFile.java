import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Set;

public class ReadFile {
    public static void main(String[] args) {

        Scanner userScanner = new Scanner(System.in);  // Create a Scanner object*
        System.out.println("****************************************************************************");
        System.out.println("Welcome to the Moby Dick word frequency analysis. Press <Enter> to continue.");
        System.out.println("****************************************************************************");
        String entry = userScanner.nextLine();  // Read user input
        userScanner.close();

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

        // Read lines from text, one line at a time
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

            // Convert map to 2d array
            Object[][] wordCountAry = new Object[wordCount.size()][2];
            Set entries = wordCount.entrySet();
            Iterator entriesIterator = entries.iterator();
            
            int j = 0;
            while(entriesIterator.hasNext()){
            
                Map.Entry mapping = (Map.Entry) entriesIterator.next();
            
                wordCountAry[j][0] = mapping.getKey();
                wordCountAry[j][1] = (Integer)mapping.getValue();
            
                j++;
            }

            // Sort 2d array by 2nd dimension, i.e., the count
            Arrays.sort(wordCountAry, Comparator.comparingInt(o -> (int)o[1]));

            // Output the top 100
            // Run in reverse, because the array was sorted ascending
            System.out.println("Processing...");
            System.out.println("****************************************************************************");
            System.out.println("         The top 100 words in the provided text, mobydick.txt, are:");            
            System.out.println("****************************************************************************");
            for (int i=1; i<=100; i++) {
                int arrayIndex = wordCountAry.length-i;
                if (i<0)
                    break;
                System.out.println(i + ". " + wordCountAry[arrayIndex][0] + ": " + wordCountAry[arrayIndex][1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }  
    }
}