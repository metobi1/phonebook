package com.linkedinlearning.phonebook;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneBook {

    protected String file;
    protected final File fileFind;
    protected final File fileDir;
    protected int entries = 0;
    protected int found = 0;
    protected String[] findArray;
    protected String[] dirArray;
    protected boolean isLinear = true;
    protected static boolean isStopped = false;
    protected static boolean isHashing = false;

    long jumpSearchDifference = 0;
    long bobbleSortDifference = 0;
    long quickSortDifference = 0;
    long hashingDifference = 0;
    // long linearSearchDifference = 0;

    PhoneBook(String pathToFileFind, String pathToFileDir) {

        ProcessInput processInput = new ProcessInput();
        try { // create a string with the input data
            this.file = processInput.readFileAsString(pathToFileFind);
        } catch (IOException e) {
            System.out.printf("Error - %s%n", e.getMessage());
        }

        fileFind = new File(pathToFileFind);
        fileDir = new File(pathToFileDir);
        // get the input data into an array of the data length
        findArray = processInput.streamNamesIntoArray(fileFind);
        dirArray = processInput.streamNamesIntoArray(fileDir);
    }

    public void findNames() {
        long ZERO = 0;
        long jumpSrchDiff = 0;
        long bobSortDiff = 0;

        // perform a linear search of names
        long linSrchDiff = linearSearch();

        // display the result of the linear search
        display(linSrchDiff, jumpSrchDiff, bobSortDiff);
        isLinear = false;

        // perform a bobble sort
        String[] sortedArray = bubbleSort(linSrchDiff);

        // isStopped is triggered when the bobble sort time is more than
        // 10 times the linear sort time
        if (isStopped) {
            entries = 0;
            found = 0;
            /* switch to linear search time and display the result
             linear search time is placed where jump search is designed
             so that the search time display and addition is auto applied
             after switching to linear time*/
            display(ZERO, bobbleSortDifference, linearSearch());
            isStopped = false;
        } else {
            // this will never run for a larger directory, the sort is always
            // more than 10 times slower than linear search
            display(ZERO, bobbleSortDifference, jumpSearch(sortedArray));
        }

        // perform a quick sort
        String[] quickSortedArray = quickSort();
        // perform Binary search and display results
        display(ZERO, quickSortDifference, binarySearch(quickSortedArray));

        // perform a hash
        HashMap hashedArray = hashMap();
        // get hash and display results
        display(ZERO, hashingDifference, getPeople(hashedArray));
    }

    private void display(long linearSearchDifference,
                         long bubbleSortDifference,
                         long jumpSearchDifference) {
        TimeKeeping time = new TimeKeeping();
        // initially bubble sort and jum search are always zero so only linear
        // search values get displayed
        time.timeConv(linearSearchDifference +
                bubbleSortDifference + jumpSearchDifference);
        print(found, entries, time.min, time.sec, time.ms);

        TimeKeeping sortingTime = new TimeKeeping();
        TimeKeeping searchingTime = new TimeKeeping();
        String sortStopped = "";

        sortingTime.timeConv(bubbleSortDifference);
        searchingTime.timeConv(jumpSearchDifference);
        // this prevents the display of the below when linear search is first run
        // it is made false immediately after for bubble/jump search and display
        if (isLinear) {
            return;
        }
        // this gets triggered for the bypass of jump search as described in the
        // find names method
        if (isStopped) {
            sortStopped = sortStopped + "- STOPPED, moved to linear search";
        }

        String displayOut;

        if (isHashing) {
            displayOut = "Creating";
        } else displayOut = "Sorting";

        System.out.printf("%s time: %d min. %d sec. " +
                        "%d ms. %s\n", displayOut, sortingTime.min, sortingTime.sec,
                sortingTime.ms, sortStopped);

        System.out.printf("Searching time: %d min. %d sec. " +
                        "%d ms. \n", searchingTime.min, searchingTime.sec,
                searchingTime.ms);

    }

    private void print(int found, int entries, int min, int sec, int ms) {
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. " +
                "%d ms. %n", found, entries, min, sec, ms);
    }

    private long linearSearch() {
        // condition to prevent the display if linear search is run again when
        // bubble sort is 10 times slower than linear search time
        if (bobbleSortDifference < 1) {
            System.out.println("Start searching (linear search)...");
        }

        long linearSearchStartTime = System.currentTimeMillis();
        Scanner scannerFind = new Scanner(this.file);
        String findLine = "";
        while (scannerFind.hasNextLine()) {
            findLine = scannerFind.nextLine();
            entries++;

            for (int i = 0; i < dirArray.length; i++) {
                if (dirArray[i].contains(findLine)) {
                    found++;
                    break;
                }
            }
        }
        long linearSearchDifference = System.currentTimeMillis() -
                linearSearchStartTime;
        return linearSearchDifference;
    }

    private String[] bubbleSort(long linearSearchDifference) {
        System.out.println("Start searching (bubble sort + jump search)...");
        long bobbleSortStartTime = System.currentTimeMillis();
        // the linear search time is passed into the method so that the time
        // taken by bubble sort can be compared to linear search time
        String[] sortedArray = BubbleSort.strArraySort(dirArray,
                linearSearchDifference);
        bobbleSortDifference = System.currentTimeMillis() - bobbleSortStartTime;
        return sortedArray;
    }

    private long jumpSearch(String[] sortedArray) {

        long jumpSearchStartTime = System.currentTimeMillis();
        JumpSearch.jumpSearch(sortedArray, findArray);
        entries = JumpSearch.entries;
        found = JumpSearch.found;
        long jumpSearchEndTime = System.currentTimeMillis();
        jumpSearchDifference = jumpSearchEndTime - jumpSearchStartTime;

        return jumpSearchDifference;
    }

    private String[] quickSort() {
        System.out.println("Start searching (quick sort + binary search)...");
        long quickSortStartTime = System.currentTimeMillis();
        String[] sortedArray = QuickSort.quickSort(dirArray, 0,
                dirArray.length - 1);
        quickSortDifference = System.currentTimeMillis() - quickSortStartTime;

        return sortedArray;
    }

    private long binarySearch(String[] sortedArray) {
        long binarySearchStartTime = System.currentTimeMillis();
        BinarySearch.binarySearch(sortedArray, findArray);
        entries = BinarySearch.entries;
        found = BinarySearch.found;
        long binarySearchEndTime = System.currentTimeMillis();

        return binarySearchEndTime - binarySearchStartTime;
    }

    private HashMap hashMap() {
        isHashing = true;
        System.out.println("Start searching (hash table)...");
        long hashingStartTime = System.currentTimeMillis();
        HashMap hashedArray = HashedTable.hasTable2(dirArray);
        hashingDifference = System.currentTimeMillis() - hashingStartTime;

        return hashedArray;
    }

    private long getPeople(HashMap directory) {
        long getPeopleStartTime = System.currentTimeMillis();
        HashedTable.getPeople(findArray, directory);
        entries = HashedTable.entries;
        found =HashedTable.found;
        long getPeopleEndTime = System.currentTimeMillis();

        return getPeopleEndTime - getPeopleStartTime;
    }
}
