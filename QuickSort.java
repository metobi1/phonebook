package com.linkedinlearning.phonebook;

public class QuickSort {

    public static String[] quickSort(String[] strToSort, int start, int end) {
        // Chose a median pivot
        int mid = (start + end) / 2;
        // The swap positions should always start at the array beginning
        int swpPos = start;
        // moving the chosen pivot out of the way to make it easy to swap/move
        // bigger and smaller characters to the left and right of the pivot
        String temp = strToSort[mid];
        strToSort[mid] = strToSort[end];
        strToSort[end] = temp;

        for (int i = start; i <= end; i++) {
            if (i == end) {
                // move the pivot back to the correct position from the edge
                temp = strToSort[swpPos];
                strToSort[swpPos] = strToSort[end];
                strToSort[end] = temp;
                // checking for characters to be sorted on the left and right
                // of the current pivot
                if ((swpPos - 1) > start && (swpPos + 1) < end) {
                    quickSort(strToSort, start, swpPos - 1);
                    quickSort(strToSort, swpPos + 1, end);
                } // if there are chars only on the left of the pivot
                else if ((swpPos - 1) > start) {
                    quickSort(strToSort, start, swpPos - 1);
                } // if there are chars only on the right of the pivot
                else if ((swpPos + 1) < end) {
                    quickSort(strToSort, swpPos + 1, end);
                }
                // checking if the chars are smaller than the pivot
                // sorting in ascending order
            } else if ( compStrings(strToSort[i], strToSort[end]) ) {
                temp = strToSort[swpPos];
                strToSort[swpPos] = strToSort[i];
                strToSort[i] = temp;
                swpPos = swpPos + 1;
            }
        }
        return strToSort;
    }

    protected static boolean compStrings(String firStr, String secStr) {
        // removing the phone numbers from the input strings to only
        // compare the names
        String stripedFirStr = splitString(firStr);
        String stripedSecStr = splitString(secStr);

        int firStrInt = stripedFirStr.length();
        int secStrInt = stripedSecStr.length();
        boolean isLessOrEqual = false;

        for (int i = 0; i < stripedFirStr.length(); i++) {
            // if the strings are of equal length
            if (firStrInt == secStrInt) {
                if (stripedFirStr.charAt(i) == stripedSecStr.charAt(i)) {
                    isLessOrEqual = true;
                } else if (stripedFirStr.charAt(i) > stripedSecStr.charAt(i)) {
                    return false;
                } else if (stripedFirStr.charAt(i) < stripedSecStr.charAt(i)) {
                    return true;
                }// if the first string is longer than the second
            }else if (firStrInt > secStrInt) {
                if (stripedFirStr.charAt(i) > stripedSecStr.charAt(i)) {
                    return false;
                } else if (stripedFirStr.charAt(i) < stripedSecStr.charAt(i)) {
                    return true;
                } else if (i == secStrInt - 1) {
                    return false;
                }// if the second string is longer than the first
            } else if (stripedFirStr.charAt(i) > stripedSecStr.charAt(i)) {
                return false;
            } else if (stripedFirStr.charAt(i) < stripedSecStr.charAt(i)) {
                return true;
            } else if (i == firStrInt - 1) {
                return true;
            }
        }
        return isLessOrEqual;
    }

    protected static String splitString(String strToSplit) {

        int count = 0;
        int strLen = strToSplit.length();
        // count the number of characters
        // of the phone number
        for (int i = 0; i < strLen; i++) {
            if (strToSplit.charAt(i) >= '0' &&
                    strToSplit.charAt(i) <= '9') {
                count++;
                continue;
            }
            break;
        }
        // include the space before name starts
        int beginInd = count + 1;
        // start string from first letter of name
        //System.out.println("split");
        return strToSplit.substring(beginInd, strLen);
    }
}
