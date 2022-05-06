package com.linkedinlearning.phonebook;

import java.util.HashMap;

public class HashedTable {

    static int entries = 0;
    static int found = 0;

    static HashMap hasTable2(String[] arrayToHash) {

        HashMap<String, String> hashedDir = new HashMap<>();
        int size = arrayToHash.length;

        for (int i = 0; i < size; i++) {
            String strElem = splitString(arrayToHash[i]);
            hashedDir.put(strElem, arrayToHash[i]);
        }
        return hashedDir;
    }

    static void getPeople(String[] findPeople, HashMap directory) {

        for (int i = 0; i < findPeople.length; i++) {
            entries++;
            directory.get(findPeople[i]);
            found++;
        }
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
