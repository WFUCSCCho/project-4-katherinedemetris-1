/****************************************************************************************
 * @file: Proj4.java
 * @description: This program analyzes the performance of a separate chaining hash table
 *          using housing price data. It reads data from a file, creates sorted,
 *          shuffled, and reversed versions of the data, then measures and compares
 *          the time taken for insert, search, and delete operations on each version.
 *          Results are displayed to console and saved to analysis.txt in CSV format.
 * @author: Katherine Demetris
 * @date: December 3, 2024
 ****************************************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java Proj4 <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // FINISH ME
        // Create ArrayList to store housing data
        ArrayList<HousingPricesData> housingPriceList = new ArrayList<>();

        // Read and parse data from the file
        int lineCount = 0;
        while (inputFileNameScanner.hasNextLine() && lineCount < numLines) {
            String line = inputFileNameScanner.nextLine();
            String[] parts = line.split(",");

            if (parts.length == 13) {
                try {
                    // Parse price by removing $ and , characters
                    String priceStr = parts[4].replaceAll("[$,]", "").trim();
                    int price = priceStr.isEmpty() ? 0 : Integer.parseInt(priceStr);

                    HousingPricesData data = new HousingPricesData(
                            parts[0], parts[1],  // suburb, address
                            parts[2].isEmpty() ? 0 : Integer.parseInt(parts[2]),  // rooms
                            parts[3].isEmpty() ? ' ' : parts[3].charAt(0),  // type
                            price,  // using cleaned price value
                            parts[5].isEmpty() ? ' ' : parts[5].charAt(0),  // method
                            parts[6], parts[7],  // sellerG, date
                            parts[8].isEmpty() ? 0 : Integer.parseInt(parts[8]),  // postcode
                            parts[9],  // regionName
                            parts[10].isEmpty() ? 0 : Integer.parseInt(parts[10]),  // propertyCount
                            parts[11].isEmpty() ? 0.0 : Double.parseDouble(parts[11]),  // distance
                            parts[12]  // councilArea
                    );
                    housingPriceList.add(data);
                    lineCount++;
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing line: " + line);
                    System.err.println("Specific error: " + e.getMessage());
                }
            }
        }

        // Create sorted, randomized, and reversed copies of the data
        ArrayList<HousingPricesData> sortedData = new ArrayList<>(housingPriceList);
        ArrayList<HousingPricesData> shuffledData = new ArrayList<>(housingPriceList);
        ArrayList<HousingPricesData> reversedData = new ArrayList<>(housingPriceList);

        Collections.sort(sortedData);
        Collections.shuffle(shuffledData);
        Collections.sort(reversedData, Collections.reverseOrder());

        // Set up analysis file for writing results
        File analysisFile = new File("analysis.txt");
        boolean fileExists = analysisFile.exists();
        FileWriter analysisWriter = new FileWriter("analysis.txt", true);

        // Write header to analysis.txt if it's a new file
        if (!fileExists) {
            analysisWriter.write("Operation,Dataset Type,Number of Lines,Time (ms)\n");
        }

        // Print header to console for results display
        System.out.println("\nResults for " + numLines + " lines:");
        System.out.println("-------------------------------------------");
        System.out.printf("%-12s %-12s %-10s%n", "Operation", "Dataset", "Time (ms)");
        System.out.println("-------------------------------------------");

        // Create hash table instance for testing
        SeparateChainingHashTable<HousingPricesData> hashTable = new SeparateChainingHashTable<>();

        // Test with sorted data
        testHashTable(hashTable, sortedData, "Sorted", numLines, analysisWriter);

        // Clear hash table and test with shuffled data
        hashTable.makeEmpty();
        testHashTable(hashTable, shuffledData, "Shuffled", numLines, analysisWriter);

        // Clear hash table and test with reversed data
        hashTable.makeEmpty();
        testHashTable(hashTable, reversedData, "Reversed", numLines, analysisWriter);

        // Close the analysis file
        analysisWriter.close();
        System.out.println("\nResults have been appended to analysis.txt");
    }

    /**
     * Tests hash table operations (insert, search, delete) on a given dataset
     * and records the timing results both to console and analysis file
     *
     * @param hashTable The hash table to perform operations on
     * @param data The dataset to use for testing
     * @param datasetType The type of dataset (Sorted, Shuffled, or Reversed)
     * @param numLines The number of lines in the dataset
     * @param analysisWriter FileWriter for writing results to analysis.txt
     * @throws IOException If there's an error writing to the file
     */
    private static void testHashTable(SeparateChainingHashTable<HousingPricesData> hashTable,
                                      ArrayList<HousingPricesData> data,
                                      String datasetType,
                                      int numLines,
                                      FileWriter analysisWriter) throws IOException {
        // Test and time insert operation
        long startTime = System.nanoTime();
        for (HousingPricesData house : data) {
            hashTable.insert(house);
        }
        long endTime = System.nanoTime();
        double insertTime = (endTime - startTime) / 1000000.0;

        // Record insert results
        System.out.printf("%-12s %-12s %-10.2f%n", "Insert", datasetType, insertTime);
        analysisWriter.write(String.format("Insert,%s,%d,%.2f%n", datasetType, numLines, insertTime));

        // Test and time search operation
        startTime = System.nanoTime();
        for (HousingPricesData house : data) {
            hashTable.contains(house);
        }
        endTime = System.nanoTime();
        double searchTime = (endTime - startTime) / 1000000.0;

        // Record search results
        System.out.printf("%-12s %-12s %-10.2f%n", "Search", datasetType, searchTime);
        analysisWriter.write(String.format("Search,%s,%d,%.2f%n", datasetType, numLines, searchTime));

        // Test and time delete operation
        startTime = System.nanoTime();
        for (HousingPricesData house : data) {
            hashTable.remove(house);
        }
        endTime = System.nanoTime();
        double deleteTime = (endTime - startTime) / 1000000.0;

        // Record delete results
        System.out.printf("%-12s %-12s %-10.2f%n", "Delete", datasetType, deleteTime);
        analysisWriter.write(String.format("Delete,%s,%d,%.2f%n", datasetType, numLines, deleteTime));
    }
}