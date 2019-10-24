package edu.hcmiu.t3tr1s.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A utility class to load text files as strings.
 */

public class FileUtils {

    private FileUtils() {
    }

    /**
     * Create a string containing all the data in a text file.
     * @param file path to the text file.
     * @return a String object constructed from the text file.
     */

    public static String loadAsString(String file) {

        StringBuilder result = new StringBuilder();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String buffer = "";

            while ((buffer = reader.readLine()) != null)
                result.append(buffer + '\n');

        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
