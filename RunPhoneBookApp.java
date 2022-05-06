package com.linkedinlearning.phonebook;

public class RunPhoneBookApp {

    static void runPhoneBookApp() {

        String pathToFileFind = "C:\\Users\\Tobi Oluwole\\Downloads\\find.txt";
        String pathToFileDir = "C:\\Users\\Tobi Oluwole\\Downloads\\directory.txt";
        runPhoneBook(pathToFileFind, pathToFileDir);
    }

    static void runPhoneBook(String pathToFileFind, String pathToFileDir) {

        PhoneBook phoneBook = new PhoneBook(pathToFileFind, pathToFileDir);

        phoneBook.findNames();

    }
}
