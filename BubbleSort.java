package com.linkedinlearning.phonebook;

public class BubbleSort {

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

    private static boolean compStrings(String inputBigStr, String inputSmallStr) {
        // split phone numbers from the string
        String bigStr = splitString(inputBigStr);
        String smallStr = splitString(inputSmallStr);

        int bigStrInt = bigStr.length();
        int smallStrInt = smallStr.length();

        for (int i = 0; i < bigStrInt; i++) {
            // when 1st string is longer than the second
            if (bigStrInt > smallStrInt) {
                if (bigStr.charAt(i) > smallStr.charAt(i)) {
                    return true;
                } else if (bigStr.charAt(i) < smallStr.charAt(i)) {
                    return false;
                } else if (i == smallStrInt - 1) {
                    return true;
                }

            } else if (bigStr.charAt(i) > smallStr.charAt(i)) {
                return true;
            } else if (bigStr.charAt(i) < smallStr.charAt(i)) {
                return false;
            }
        }
        return false;
    }

    static String[] strArraySort(String[] strArrayInput,
                                 long linearSearchDifference) {
        String tempString = "";
        String biggestString = "";
        int stringToSortLen = strArrayInput.length;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < stringToSortLen; i++) {
            long difference = System.currentTimeMillis()
                    - startTime;
            if (difference > linearSearchDifference * 10) {
                PhoneBook.isStopped = true;
                return new String[]{"Stopped"};
            }

            for (int j = 0; j < stringToSortLen; j++) {
                /* at this j, the string is already sorted
                e.g {"7", "5", "4", "3", "2"}
                when i = 1 ; {"4", "3", "2", "5", "7"} */
                if (i > 0 && j == (stringToSortLen - i)) {
                    break;
                } // load string to compare
                if (j == 0) {
                    biggestString = strArrayInput[j];
                    continue;
                }
                /* compare 1st string with 2nd string and swap if
                 the 1st string is bigger*/
                if (compStrings(biggestString, strArrayInput[j])) {
                    tempString = strArrayInput[j];
                    strArrayInput[j] = biggestString;
                    strArrayInput[j - 1] = tempString;
                    //System.out.println("sorted " + i);
                } else {/* if the current string is smaller or same
                load the next string same*/
                    biggestString = strArrayInput[j];
                }
            }
        }
        return strArrayInput;
    }
}
