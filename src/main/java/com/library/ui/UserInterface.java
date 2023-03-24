package com.library.ui;


import java.util.Scanner;

import com.library.model.Library;

public class UserInterface {
    
    private Library library;
    private Scanner scanner;
    
    //constructer for creating or starting up library and scanner
    public UserInterface() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }
    //starting up application and presenting selection options 
    public void start() {
        System.out.println("Welcome to the Library Management System!");
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. View all books");
            System.out.println("2. Search for a book");
            System.out.println("3. Check out a book");
            System.out.println("4. Return a book");
            System.out.println("5. Add a new book");
            System.out.println("6. Add books from file");
            System.out.println("0. Quit");
            
            int choice = getIntInput();

            //all cases with given functions for different selections
            switch (choice) {
                case 1:
                    library.viewAllBooks();
                    break;
                case 2:
                    searchMenu();
                    break;
                case 3:
                    checkoutMenu();
                    break;
                case 4:
                    returnMenu();
                    break;
                case 5:
                    addBookMenu();
                    break;
                case 6:
                    importBooksMenu();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    //display menu for searching library
    private void searchMenu() {
        System.out.println("Please choose a search option:");
        System.out.println("1. Search by title");
        System.out.println("2. Search by author");
        System.out.println("3. Search by ISBN");
        System.out.println("0. Go back");
        
        int choice = getIntInput();

        //different input options and search functions for all search menu options
        switch (choice) {
            case 1:
                System.out.print("Enter title to search for: ");
                String title = scanner.nextLine();
                library.searchBooksByTitle(title);
                break;
            case 2:
                System.out.print("Enter author to search for: ");
                String author = scanner.nextLine();
                library.searchBooksByAuthor(author);
                break;
            case 3:
                System.out.print("Enter ISBN to search for: ");
                String isbn = scanner.nextLine();
                library.searchBooksByISBN(isbn);
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    
    //takes input for isbn and patron, then applies them to the checkoutbook function
    private void checkoutMenu() {
        System.out.print("Enter ISBN of book to check out: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter patron name: ");
        String patron = scanner.nextLine();
        library.checkOutBook(isbn, patron);
    }
    
    //takes input for isbn, then runs returnbook function
    private void returnMenu() {
        System.out.print("Enter ISBN of book to return: ");
        String isbn = scanner.nextLine();
        library.returnBook(isbn);
    }
    
    //takes input for title, author, and isbn, and applies them to addbook function
    private void addBookMenu() {
        System.out.print("Enter title of new book: ");
        String title = scanner.nextLine();
        System.out.print("Enter author of new book: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN of new book: ");
        String isbn = scanner.nextLine();
        library.addBook(title, author, isbn);
    }
    
    //sets scanner to watch for input, sends error if not int
    private int getIntInput() {
        while (true) {
            try {
                System.out.print("> ");
                int choice = Integer.parseInt(scanner.nextLine());
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    //takes input path/file and applies to importbooks function
    private void importBooksMenu(){
        System.out.println("Enter the name of file to import");
        String fileName = scanner.nextLine();
        library.importBooksFromFile(fileName);
    }
}