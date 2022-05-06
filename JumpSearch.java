package com.linkedinlearning.phonebook;

import java.util.Objects;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public class JumpSearch {

    protected static int entries = 0;
    protected static int found = 0;
    static int lastInd = 0;

    static int blockSize(int length) {
        int block = (int) floor(sqrt(length));
        return block;
    }

    public static void jumpSearch(String[] inputStr, String[] searchForStr) {
        int lenSearch = searchForStr.length;
        for (int i = 0; i < lenSearch; i++) {
            int lenInput = inputStr.length;
            entries++;
            printFoundIndex(1, i, blockSize(lenInput),
                    inputStr, searchForStr);
        }
    }

    static void printFoundIndex(int init, int i, int len,
                                String[] inputStr, String[] searchForStr) {
        int lenInput = inputStr.length;
        for (int j = init; j <= lenInput; j+= len) {

            String compare = compStrings(BubbleSort.splitString
                    (inputStr[j - 1]), searchForStr[i]);

            if ( "Equal".equals(compare)) {
                //System.out.println(j - 1);
                found++;
                break;
            } else if ("Less".equals(compare)) {
                lastInd = j;
            } else if("Greater".equals(compare) ) {
                int result = backwardLinearSearch (j - 1, lastInd,
                        inputStr, searchForStr[i]);
                if (result == -1) {
                    //System.out.println(result);
                    break;
                } else //System.out.println(result);
                found++;
                break;
            }
            if (j + len > inputStr.length) {
                int newLength = inputStr.length - j;
                printFoundIndex(j, i, blockSize(newLength),
                        inputStr, searchForStr);
                break;
            }
        }
    }

    static String compStrings(String firStr, String secStr) {
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

    static int backwardLinearSearch (int i, int lastInd,
                                     String[] inputNums, String target) {
        int result = 0;
        for (int j = i; j >= lastInd; j--) {
            if (Objects.equals(BubbleSort.splitString
                    (inputNums[j]), target)) {
                result = j;
                break;
            } else {
                result = -1;
            }
        }
        return result;
    }
}
