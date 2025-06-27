import java.io.*;
import java.util.Scanner;

public class NotesManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "note.txt";

        while (true) {
            System.out.println("\n=== Text-Based Notes Manager ===");
            System.out.println("1. Write a Note");
            System.out.println("2. Read All Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    writeNote(scanner, fileName);
                    break;
                case 2:
                    readNotes(fileName);
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Please choose 1, 2, or 3.");
            }
        }
    }

    // Method to write note using FileWriter
    public static void writeNote(Scanner scanner, String fileName) {
        System.out.println("Enter your note (type 'END' on a new line to finish):");

        StringBuilder note = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equalsIgnoreCase("END")) {
            note.append(line).append(System.lineSeparator());
        }

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(note.toString());
            writer.write("------\n"); // separator
            System.out.println("Note saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    // Method to read notes using FileReader and BufferedReader
    public static void readNotes(String fileName) {
        System.out.println("\n--- Saved Notes ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isEmpty = true;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                isEmpty = false;
            }

            if (isEmpty) {
                System.out.println("(No notes found in file.)");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Add a note first.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
