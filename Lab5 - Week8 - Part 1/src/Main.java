import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    private static void printToFile(String filePath, Object object) {
        try (PrintStream printStream = new PrintStream(filePath)) {
            printStream.println(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void printMenu(){
        System.out.println("\n\n0. Exit");
        System.out.println("1. Print non-terminals");
        System.out.println("2. Print terminals");
        System.out.println("3. Print starting symbol");


    }

    public static void runGrammar(){
        Grammar grammar = new Grammar("Input_Output/G2.txt");
        boolean notStopped = true;
        while(notStopped) {
            printMenu();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter your option");
            int option = keyboard.nextInt();
            switch (option) {
                case 0:
                    notStopped = false;
                    break;
                case 1:
                    System.out.println("\n\nNon-terminals -> " + grammar.getNonTerminals());
                    break;
                case 2:
                    System.out.println("\n\nTerminals -> " + grammar.getTerminals());
                    break;
                case 3:
                    System.out.println("\n\nStarting symbol -> " + grammar.getStartingSymbol());
                    break;

            }
        }

        launchApp();
    }


    public static void launchApp(){
        System.out.println("0. Exit");
        System.out.println("1. Grammar");
        System.out.println("Your option: ");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                runGrammar();
                break;

            case 0:
                break;

            default:
                System.out.println("Invalid command!");
                break;

        }
    }

    public static void main(String[] args) {
        launchApp();
    }
}
