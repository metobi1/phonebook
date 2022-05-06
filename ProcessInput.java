package com.linkedinlearning.phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProcessInput {
    // get a complete copy of the input data
    public String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
    // get the input data into an oversize array
    public String[] streamNamesIntoArray(File fileDir) {
        int i = 0;
        /* create an overlarge array to get the input data
         with unknown length */
        String[] nullsArray = new String[10_000_000];
        try (Scanner scanner = new Scanner(fileDir)) {
            while(scanner.hasNextLine()) {
                nullsArray[i] = scanner.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Error: \n", e.getMessage());
        }

        return removeNulls(nullsArray);
    }
    // shrink the array to the correct data size
    private String[] removeNulls(String[] nameArray) {
        int len = 0;
        // get the exact length of the array before excess null
        for (int i = 0; i < nameArray.length; i++) {
            if (nameArray[i + 1] == null) {
                len = i + 1;
                break;
            }
        }
        // use the length to create an array
        String[] noNullArray = new String[len];

        for (int i = 0; i < noNullArray.length; i++) {
            noNullArray[i] = nameArray[i];
        }
        return noNullArray;
    }
}
