package com.linkedinlearning.phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class PeekSolutionTest {
    public static void main(String[] args) {
        System.out.println("Start searching...");
        File directoryFile = new File("C:\\Users\\Learning PC\\Downloads\\directory.txt");
        File findFile = new File("C:\\Users\\Learning PC\\Downloads\\find.txt");
        int totalFound =0;
        int total =0;
        long start = System.currentTimeMillis();
        try(Scanner sc= new Scanner(findFile)){
            while(sc.hasNext()){
                String findName = sc.nextLine();
                total++;
                //System.out.println("total:"+total);
                try(Scanner s = new Scanner(directoryFile)){
                    while(s.hasNext()){
                        String nameAndPhone = s.nextLine();
                        String[] details = nameAndPhone.split(" ",2);
                        if(Objects.equals(details[1], findName)){
                            totalFound++;
                            //System.out.println("totalFound:"+totalFound);
                            break;
                        }
                    }

                }catch (FileNotFoundException e){
                    System.out.println("file not found:"+e.getMessage());
                }
            }


        }catch (FileNotFoundException e){
            System.out.println("file not found:"+e.getMessage());
        }
        long end = System.currentTimeMillis();
        long totalTimeTaken= end-start;
        //System.out.println("totaltimetaken:"+totalTimeTaken);
        int min = (int) (totalTimeTaken/1000)/60;
        // int hr = min/60;
        //int m = min %60;
        int sec = (int) (totalTimeTaken/1000)%60;
        int ms = (int)totalTimeTaken-(min *60000 + sec *1000 );
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                totalFound,total,min,sec,ms);

    }
}
