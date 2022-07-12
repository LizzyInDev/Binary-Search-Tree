import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Files {
    private String[] input;
    private int totalWordCount = 0;

    public int countWords(String fileInput) {
        try {
            File file = new File(fileInput);
            Scanner scannerFile = new Scanner(file);

            while (scannerFile.hasNextLine()) {
                String temp = scannerFile.nextLine();
                String[] tempLine = temp.split(" ");
                for (String word : tempLine) {
                    String sTemp = word.replaceAll("[^\\w\\s]","");
                    if (sTemp.length() >= 3)
                        totalWordCount++;
                }
            }
            scannerFile.close();
        } catch (IOException e) {
            System.out.println(fileInput + " not found. Please try again");
            System.exit(0);
        }
        return totalWordCount;
    }

    /*
    * reads INPUT.txt
    * ignores the case of every word
    * removes special characters from the words
    * accepts words that are at least 3 of length
    * */
    public int readFile(String fileInput) {
        countWords(fileInput);
        input = new String[totalWordCount];

        int error = 0;
        int countInput = 0;

        try {
            File file = new File(fileInput);
            Scanner scannerFile = new Scanner(file);

            while (scannerFile.hasNextLine()) {
                String temp = scannerFile.nextLine();
                String[] tempLine = temp.split(" ");
                for (String word : tempLine) {
                    String sTemp = word.replaceAll("[^\\w\\s]","");
                    if (sTemp.length() >= 3) {
                        input[countInput] = sTemp.toLowerCase(Locale.ROOT);
                        countInput++;
                    }
                }
            }
            scannerFile.close();
        } catch (IOException e) {
            System.out.println(fileInput + " not found. Please try again");
            error = 1;
        }
        return error;
    }

    /*
    * creates and/or writes to WORDS.txt
    * calls count() to determine the frequency count of the word in INPUT.txt
    * */
    public void writeToFile(String[] inorder, String fileInput) {
        int frequencyCount;
        String fileWords = "WORDS.txt";

        try {
            File file = new File(fileWords);
            FileWriter fw = new FileWriter(file.getName(), false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < inorder.length; i++) {
                if (!Objects.equals(inorder[i], null)) {
                    String key = inorder[i];
                    frequencyCount = count(key, fileInput);
                    String foo = String.format("%-20s %s", key, frequencyCount);
                    bw.write(foo);
                    bw.newLine();
                }
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    /*
    * counts the frequency count of a key in INPUT.txt
    * */
    public int count(String key, String fileInput) {
        int counter = 0;
        readFile(fileInput);
        String[] temp = getInput();
        for (String s : temp)
            if (Objects.equals(s, key))
                counter++;

        return counter;
    }

    /*
    * returns the array of filtered words found in INPUT.txt
    * */
    public String[] getInput() {
        return input.clone();
    }
}
