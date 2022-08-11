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

    public static void main(String[] args) {

        /* OPEN FILE */

        // Initialise variable to sort file contents
        String line = "";

        // Create ArrayList for file data
        ArrayList<String> data = new ArrayList<String>();

        // Get filePath from user
        String filePath = JOptionPane.showInputDialog(null, "Please enter the file path of your CSV file.\n\nFor example:\n/Users/johnsmith/Desktop/file.csv", "Enter file path", JOptionPane.INFORMATION_MESSAGE);

        // If file opened is not .csv, show error message.
        if ((filePath.charAt(filePath.length() - 1) != 'v' || filePath.charAt(filePath.length() - 2) != 's') || (filePath.charAt(filePath.length() - 3) != 'c' || filePath.charAt(filePath.length() - 4) != '.')) {
            JOptionPane.showMessageDialog(null, "The file at the specified filepath does not have the\nextension .csv making it incompatible. Please enter\nthe file path of a .csv file and try again.", "Incompatible file extension...", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        /* READ & SORT DATA FROM FILE */

        // Attempt to open the file
        try {
            BufferedReader br = new BufferedReader(new FileReader("kjbdvjhds/input.csv"));

            // Read file into data ArrayList line by line
            while ((line = br.readLine()) != null) {
                data.add(line);
            }

        // If file not fount, show error message and end program
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not find input.csv file at specified file path...\n\nMake sure the file exists where specified and that the\nfile path is correct and try again.", "No file found...", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Sort data
        Collections.sort(data);

        /* WRITE TO OUTPUT FILE */

        // Create filePath for output file
        String outputFilePath = filePath.substring(0, filePath.length() - 4) + " (SORTED).csv";

        // Create output file
        File outputCSV = new File(outputFilePath);

        // Open output file to be written to
        try {
            FileWriter myWriter = new FileWriter("/Users/ilyasbaqqari/Desktop/input.csv");
            myWriter.write("");

            // Iterate through arrays writing each entry to file
            for (int i = 0; i < data.size(); i++) {
                myWriter.append(data.get(i)+"\n");
            }

            // Close file and show success message
            myWriter.close();
            JOptionPane.showMessageDialog(null, "File has been sorted successfully!\n\nFilepath:\n" + outputFilePath, "Sort success", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);

        // If writing to file fails, show error message
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not output to file...\n\nPlease check the location specified can be written to and try again.", "Could not output...", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}