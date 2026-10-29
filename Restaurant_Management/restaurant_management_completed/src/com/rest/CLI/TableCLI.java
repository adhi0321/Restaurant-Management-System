package com.rest.CLI;

import com.rest.models.Table;
import com.rest.service.TableService;
import java.util.Scanner;

public class TableCLI {
    private final TableService service;
    private final Scanner scanner;

    public TableCLI(TableService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== RESTAURANT TABLE MANAGEMENT ===");
            System.out.println("1. View all tables");
            System.out.println("2. Occupy a table");
            System.out.println("3. Free a table");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n=== ALL TABLES ===");
                    for (Table table : service.getAllTables()) {
                        System.out.println(table);
                    }
                    break;
                case 2:
                    changeTableStatus(true);
                    break;
                case 3:
                    changeTableStatus(false);
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using Restaurant Table Management!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 0, 1, 2, or 3.");
            }
        }
        scanner.close();
    }

    private void changeTableStatus(boolean occupy) {
        System.out.print("Enter table ID: ");
        String input = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.out.println("Table ID must be a number.");
            return;
        }

        boolean changed = occupy ? service.occupyTable(id) : service.freeTable(id);
        if (changed) {
            System.out.println(occupy ? "Table occupied successfully." : "Table freed successfully.");
        } else {
            System.out.println("Could not change status. Check the table ID and current status.");
        }
    }
}
