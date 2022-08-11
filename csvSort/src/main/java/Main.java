// File Handling Packages
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// Array Packages
import java.util.ArrayList;
import java.util.Collections;
// GUI Package
import javax.swing.JOptionPane;

public class Main {

    // Define filePath variable
    static String filePath;

    public static void main(String[] args) {
        // Call CSV Sorter Method
        csvSort();
    }

    // CSV Sort Method
    public static void csvSort() {

        // Read, Sort, and Write
        ArrayList<String> fileData = readFile();
        Collections.sort(fileData);
        writeToFile(fileData);

    }

    // Read File Method
    static ArrayList<String> readFile() {

        // Initialise variable to sort file contents
        String line = "";

        // Create ArrayList for file data
        ArrayList<String> fileData = new ArrayList<String>();

        // Get filePath from user
        filePath = JOptionPane.showInputDialog(null, "Please enter the file path of your CSV file.\n\nFor example:\n/Users/johnsmith/Desktop/file.csv", "Enter file path", JOptionPane.INFORMATION_MESSAGE);

        // If input is empty, show error message and try again
        if (filePath.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please enter the file path of a .csv file and try again.", "Empty input...", JOptionPane.ERROR_MESSAGE);
            csvSort();
        }

        // If file opened is not .csv, show error message and restart
        if ((filePath.charAt(filePath.length() - 1) != 'v' || filePath.charAt(filePath.length() - 2) != 's') || (filePath.charAt(filePath.length() - 3) != 'c' || filePath.charAt(filePath.length() - 4) != '.')) {
            JOptionPane.showMessageDialog(null, "The file at the specified filepath does not have the\nextension .csv making it incompatible. Please enter\nthe file path of a .csv file and try again.", "Incompatible file extension...", JOptionPane.ERROR_MESSAGE);
            csvSort();
        }

        // Attempt to open the file
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(filePath));

            // Read file into data ArrayList line by line
            while ((line = fileReader.readLine()) != null) {
                fileData.add(line);
            }

        // If file not fount, show error message and restart
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not find file at specified file path...\n\nMake sure the file exists where specified and that the\nfile path is correct and try again.", "No file found...", JOptionPane.ERROR_MESSAGE);
            csvSort();
        }

        return fileData;
    }

    // Write File Method
    static void writeToFile(ArrayList<String> fileData) {

        // Create filePath for output file
        String outputFilePath = filePath.substring(0, filePath.length() - 4) + " (SORTED).csv";

        // Create output file
        File outputCSV = new File(outputFilePath);

        // Open output file to be written to
        try {
            FileWriter myWriter = new FileWriter(outputFilePath);
            myWriter.write("");

            // Iterate through arrays writing each entry to file
            for (int i = 0; i < fileData.size(); i++) {
                myWriter.append(fileData.get(i) + "\n");
            }

            // Close file and show success message
            myWriter.close();
            JOptionPane.showMessageDialog(null, "File has been sorted successfully!\n\nFilepath:\n" + outputFilePath, "Sort success", JOptionPane.INFORMATION_MESSAGE);

        // If writing to file fails, show error message and restart
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not output to file...\n\nPlease check the location specified can be written to and try again.", "Could not output...", JOptionPane.ERROR_MESSAGE);
            csvSort();
        }
    }
}