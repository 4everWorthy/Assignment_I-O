package org.example;

import java.io.*;
import java.util.logging.*;
import java.util.*;

public class IOMerge {

    private static final Logger logger = Logger.getLogger(IOMerge.class.getName());

    public static void main(String[] args) {
        // Create the input files if they do not exist
        createInputFile("input1.txt", Arrays.asList(1, 3, 5, 7, 9));
        createInputFile("input2.txt", Arrays.asList(2, 3, 4, 5, 6));

        // Initialize Lists
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> mergedList = new ArrayList<>();

        // Read integers from input1.txt
        try (BufferedReader br = new BufferedReader(new FileReader("input1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int num = Integer.parseInt(line);  // Convert each line to an integer
                list1.add(num);  // Add the integer to list1
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File input1.txt not found.", e);
        } catch (IOException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Error reading from input1.txt.", e);
        }

        // Read integers from input2.txt
        try (BufferedReader br = new BufferedReader(new FileReader("input2.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int num = Integer.parseInt(line);
                list2.add(num);  // Add the integer to list2
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File input2.txt not found.", e);
        } catch (IOException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Error reading from input2.txt.", e);
        }

        // Merge lists
        mergedList.addAll(list1);  // Add all elements from list1
        mergedList.addAll(list2);  // Add all elements from list2

        // Write merged list to merged.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("merged.txt"))) {
            for (int num : mergedList) {
                bw.write(Integer.toString(num));  // Write each integer to merged.txt
                bw.newLine();  // Move to the next line
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to merged.txt.", e);
        }

        // Find common integers
        Set<Integer> commonSet = new HashSet<>(list1);  // Add all elements from list1 to commonSet
        commonSet.retainAll(list2);  // Retain only the elements that are also in list2

        // Write common integers to common.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("common.txt"))) {
            for (int num : commonSet) {
                bw.write(Integer.toString(num));  // Write each common integer to common.txt
                bw.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to common.txt.", e);
        }
    }

    // Utility method to create input files
    private static void createInputFile(String filename, List<Integer> numbers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int num : numbers) {
                bw.write(Integer.toString(num));
                bw.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating " + filename, e);
        }
    }
}
