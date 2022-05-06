package com.linkedinlearning.phonebook;

public class BinarySearch {

    protected static int entries = 0;
    protected static int found = 0;

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

    public static void binarySearch(String[] strToSearch, String[] strToFind) {

        for (int i = 0; i < strToFind.length; i++) {

            int start = 0;
            int end = strToSearch.length - 1;
            int mid = (start + end) / 2;
            entries++;

            for (int j = 0; j <= strToSearch.length; j++) {

                String compare = compStrings(strToSearch[mid],
                        strToFind[i]);

                if ("Equal".equals(compare)) {
                    found++;
                    break;
                } else if ("Less".equals(compare) && (mid + 1) == end) {
                    if ("Equal".equals(compStrings(strToSearch[mid + 1],
                            strToFind[i]))) {
                        found++;
                        break;
                    } else {
                        break;
                    }
                } else if ("Less".equals(compare)) {
                    start = mid + 1;
                    mid = (start + end) / 2;
                } else if ("Greater".equals(compare) && (mid - 1) == start) {
                    if ("Equal".equals(compStrings(strToSearch[mid - 1],
                            strToFind[i]))) {
                        found++;
                        break;
                    } else {
                        break;
                    }
                } else if ("Greater".equals(compare)) {
                    end = mid - 1;
                    mid = (start + end) / 2;
                }else if (j == end) {
                }
            }
        }
    }

    static String compStrings(String firStrRaw, String secStr) {

        String firStr =  splitString(firStrRaw);

        int firStrInt = firStr.length();
        int secStrInt = secStr.length();
        boolean isEqual = false;
        boolean isGreater = false;

        for (int i = 0; i < firStr.length(); i++) {

            if (firStrInt == secStrInt) {
                if (firStr.charAt(i) == secStr.charAt(i)) {
                    isEqual = true;
                } else if (firStr.charAt(i) > secStr.charAt(i)) {
                    isGreater = true;
                    isEqual = false;
                    break;
                } else if (firStr.charAt(i) < secStr.charAt(i)) {
                    isGreater = false;
                    isEqual = false;
                    break;
                }
            }else if (firStrInt > secStrInt) {

                if (firStr.charAt(i) > secStr.charAt(i)) {
                    isGreater = true;
                    break;
                } else if (firStr.charAt(i) < secStr.charAt(i)) {
                    isGreater = false;
                    break;
                } else if (i == secStrInt - 1) {
                    isGreater = true;
                    break;
                }

            } else if (firStr.charAt(i) > secStr.charAt(i)) {
                isGreater = true;
                break;
            } else if (firStr.charAt(i) < secStr.charAt(i)) {
                isGreater = false;
                break;
            }
        }
        return equalGrtOrLess(isEqual, isGreater);
    }

    static String equalGrtOrLess(boolean isEqual, boolean isGreater) {
        if (isEqual) {
            return "Equal";
        } else if (isGreater) {
            return "Greater";
        }
        return "Less";
    }
}
