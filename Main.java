import java.io.*;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String[] rows = null;
        int numOfLines = 0;

        //Reads .txt file
        try {
            BufferedReader br = new BufferedReader(new FileReader("data"));
            String line = br.readLine();

            //counts number of lines to initialize rows
            while (line != null) {
                numOfLines++;
                line = br.readLine();
            }

            br.close();

            rows = new String[numOfLines];

            //fills rows with lines from .txt file
            BufferedReader br2 = new BufferedReader(new FileReader("data"));
            line = br2.readLine();
            int i = 0;
            while (line != null) {
                rows[i] = line;
                line = br2.readLine();
                i++;
            }

            br2.close();

        } catch (IOException e) {
            System.out.println("An error has occurred");
        }

        //creates separate arrays for all the student info
        String[] names = new String[numOfLines];
        int[] ages = new int[numOfLines];
        String[] genders = new String[numOfLines];
        String[] hometowns = new String[numOfLines];
        int[] days = new int[numOfLines];
        String[] numbers = new String[numOfLines];

        //Separates rows[] into different arrays with info separated
        for (int i = 0;i < numOfLines;i++) {
            names[i] = rows[i].substring(0, rows[i].indexOf(","));
            int comma = rows[i].indexOf(",");

            ages[i] = Integer.parseInt(rows[i].substring(comma + 1, rows[i].indexOf(",", comma + 1)));
            comma = rows[i].indexOf(",", comma + 1);

            genders[i] = rows[i].substring(comma + 1, rows[i].indexOf(",", comma + 1));
            comma = rows[i].indexOf(",", comma + 1);

            hometowns[i] = rows[i].substring(comma + 1, rows[i].indexOf(",", comma + 1));
            comma = rows[i].indexOf(",", comma + 1);

            days[i] = Integer.parseInt(rows[i].substring(comma + 1, rows[i].indexOf(",", comma + 1)));
            comma = rows[i].indexOf(",", comma + 1);

            numbers[i] = rows[i].substring(comma + 1, rows[i].length() - 1);
        }

        //Adds total of girl ages, counts number, and displays average girl age to the console
        System.out.println("Student List:");
        for (int i = 0; i < numOfLines; i++) {
            System.out.println(names[i]);
        }
        int ageSum = 0;
        int genderCount = 0;
        for (int i = 0; i < numOfLines; i++) {
            if (genders[i].equals("F")) {
                ageSum += ages[i];
                genderCount++;
            }
        }
        System.out.println("\nThe average female age is: " + (double) ageSum/genderCount);
        //Same process but for boys
        ageSum = 0;
        genderCount = 0;
        for (int i = 0; i < numOfLines; i++) {
            if (genders[i].equals("M")) {
                ageSum += ages[i];
                genderCount++;
            }
        }
        System.out.println("The average male age is: " + (double) ageSum / genderCount);

        //Displays a histogram of the number of students from each hometown
        String[] uniqueTowns = new String[hometowns.length];
        int uniqueCount = 0;

        for (String hometown : hometowns) { //counts number of unique towns, and creates a string array with all unique names
            boolean found = false;
            for (int i = 0; i < uniqueCount; i++) {
                if (uniqueTowns[i].equals(hometown)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                uniqueTowns[uniqueCount++] = hometown;
            }
        }

        int[] townCount = new int[uniqueCount];

        //counts number of people in each unique town
        for (int i = 0; i < hometowns.length; i++) {
            for (int j = 0; j < uniqueCount; j++) {
                if (hometowns[i].equals(uniqueTowns[j])) {
                    townCount[j] = townCount[j] + 1;
                }
            }
        }

        System.out.println("\nHistogram of Towns:");

        //displays histogram
        for (int i = 0; i < uniqueCount; i++) {
            System.out.print(uniqueTowns[i] + ": ");
            for (int j = 0;j < townCount[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        //Find the total amount of income:
        //1 = 35
        //2 = 30
        //3 = 25
        //4 = 20
        //5 = 15
        int income = 0;
        for (int i = 0; i < numOfLines; i++) {
            if (ages[i] == 1) income += 35;
            else if (ages[i] == 2) income += 30;
            else if (ages[i] == 3) income += 25;
            else if (ages[i] == 4) income += 20;
            else if (ages[i] == 5) income += 15;
        }
        System.out.println("\nTotal weekly income: " + income);

        //Displays a numbered list of student names, then user picks one and info about the chosen student in displayed
        System.out.println("\nFrom the list below, choose who you would like more info for:");
        for (int i = 0; i < numOfLines; i++) {
            System.out.println((i + 1) + ") " +  names[i]);
        }
        Scanner keyboard = new Scanner(System.in);
        int selection = keyboard.nextInt();

        System.out.println("\nName: " + names[selection - 1]);
        System.out.println("Age: " + ages[selection - 1]);
        System.out.println("Gender: " + genders[selection - 1]);
        System.out.println("Hometown: " + hometowns[selection - 1]);
        System.out.println("Days Attending: " + days[selection - 1]);
        System.out.println("Phone Number: " + numbers[selection - 1]);

        //Lists student info in alphabetical order of last names
        for (int i = 0; i < numOfLines; i++) {
            for (int j = 0; j < numOfLines; j++) {
                if (lastName(names[i]).compareTo(lastName(names[j])) < 0) {
                    swapString(names, i, j);
                    swapInt(ages, i, j);
                    swapString(genders, i, j);
                    swapString(hometowns, i, j);
                    swapInt(days, i, j);
                    swapString(numbers, i, j);
                    swapString(rows, i, j);
                }
            }
        }

        System.out.println();
        for (int i = 0; i < numOfLines; i++) {
            System.out.println(rows[i]);
        }

    }

    public static String lastName(String name) {
        return name.substring(name.indexOf(" ") + 1);
    }

    public static String[] swapString(String[] arr, int i1, int i2) {
        String temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        return arr;
    }

    public static int[] swapInt(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        return arr;
    }
}