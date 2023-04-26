package Assignment2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**

 The writer class provides methods for writing strings to a file.

 It creates a new file named "output.txt" if it does not already exist.
 */
public class writer {
    /**

    The BufferedWriter object used for writing to the file.
    */
    static BufferedWriter writer;

    /**

     Initializes the BufferedWriter object for writing to the "output.txt" file.
     If the file does not exist, it is created. If it does exist, its contents are overwritten.
     @throws RuntimeException if an IOException occurs while creating the BufferedWriter object.
     */
    static {
        try {
            writer = new BufferedWriter(new FileWriter("Assignment2/output.txt", false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**

     Writes a string to the file.
     @param string the string to be written.
     @param append if true, the string is appended to the end of the file. If false, the file is overwritten with the string.
     */
    public static void Print(String string, boolean append) {
        try {

            writer.write(string);
            writer.newLine();
            if (!append) {
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("ERROR: Erroneous command!");
        }
    }

}