package com.linkedinlearning.phonebook;

public class Store {

/*private final String pathToFileFind;
    private final String pathToFileDir;
    private String file;
    private String dir;
    protected int entries = 0;
    protected int found = 0;
    static int ms = 0;
    static int sec = 0;
    static int min = 0;
    static long difference = 0;
    long startTime = 0;

    PhoneBook(String pathToFileFind, String pathToFileDir) {

        this.pathToFileFind = pathToFileFind;
        this.pathToFileDir = pathToFileDir;

        try {
            this.file = readFileAsString(pathToFileFind);
            this.dir = readFileAsString(pathToFileDir);
        } catch (IOException e) {
            System.out.printf("Error - %s%n", e.getMessage());
        }
    }

    public void findNames() {

        Scanner scannerFind = new Scanner(this.file);
        String findLine = "";
        String dirLine = "";
        //int count = 0;

        while (scannerFind.hasNextLine()) {

            findLine = scannerFind.nextLine();
            entries++;
            Scanner scannerDir = new Scanner(this.dir);
            startTime = System.currentTimeMillis();

            while (scannerDir.hasNextLine()) {
                dirLine = scannerDir.nextLine();

                //count++;
                if (dirLine.contains(findLine)) {
                    long diffWhenFound = System.currentTimeMillis() - startTime;
                    difference += diffWhenFound;
                    found++;
                    break;
                }
            }
        }
        timeConversion();
    }

    private String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    static void timeConversion() {
        ms = (int) difference % 1000;
        sec = (int) difference / 1000;
        min = sec / 60;
    }*/

    /*static final Scanner scanner = new Scanner(System.in);

    static void runPhoneBookApp() {

        String pathToFileFind = "C:\\Users\\Learning PC\\Downloads\\find.txt";
        String pathToFileDir = "C:\\Users\\Learning PC\\Downloads\\directory.txt";

        PhoneBook phoneBook = new PhoneBook(pathToFileFind, pathToFileDir);
        phoneBook.findNames();

        System.out.println("Start searching...");
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. " +
                "%d ms.", phoneBook.found, phoneBook.entries, PhoneBook.min,
                PhoneBook.sec, PhoneBook.ms);

    }*/
}
