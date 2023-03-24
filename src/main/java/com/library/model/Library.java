package com.library.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    

    //Constructor for creating the object for a list of book objects
    public Library() {
        this.books = new ArrayList<>();
    }
    //Checks to see if the books object is empty, if not it prints out the contents in the desired form
    public void viewAllBooks() {
        if (this.books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        
        System.out.printf("%-30s %-30s %-20s %s\n", "Title", "Author", "ISBN", "Checked Out");
        
        for (Book book : this.books) {
            String checkedOutString = book.isCheckedOut() ? "Yes (" + book.getPatron() + ")" : "No";
            System.out.printf("%-30s %-30s %-20s %s\n", book.getTitle(), book.getAuthor(), book.getIsbn(), checkedOutString);
        }
    }

    //constructer for creating a book object to be placed within the books array object
    public void addBook(String title, String author, String isbn) {
        Book newBook = new Book(title, author, isbn);
        this.books.add(newBook);
    }
    
    //Checks to see if list of books is empty, if not removing the book object from the desired index
    public boolean deleteBook(int index) {
        if (index < 0 || index >= this.books.size()) {
            return false;
        }
        
        this.books.remove(index);
        return true;
    }
    
    //searches over list of books comparing the title varible to the given string, and placing all results within newlist to be printed.
    public List<Book> searchBooksByTitle(String searchTerm){
        List<Book> results = new ArrayList<>();
        for (Book book: this.books){
            if(book.getTitle().contains(searchTerm)){
                results.add(book);
            }
        }
        print(results);
        return results;
    }

    //searches over list of books comparing the author varible to the given string, and placing all results within newlist to be printed.
    public List<Book> searchBooksByAuthor(String searchTerm){
        List<Book> results = new ArrayList<>();
        
        for (Book book : this.books) {
            if (book.getAuthor().contains(searchTerm)) {
                results.add(book);
            }
        }
        print(results);
        return results;
    }

    //searches over list of books comparing the isbn varible to the given string, if a match is found it is then printed in the given form
    public Book searchBooksByISBN(String searchTerm){
        for (Book book : this.books) {
            if (book.getIsbn().equals(searchTerm)) {
                String checkedOutString = book.isCheckedOut() ? "Yes (" + book.getPatron() + ")" : "No";
                System.out.printf("%-30s %-30s %-20s %s\n", book.getTitle(), book.getAuthor(), book.getIsbn(), checkedOutString);
                return book;
            }
        }
        return null;
        //implement
    }
    
    //returns all contents of list "books"
    public List<Book> getAllBooks() {
        return this.books;
    }
    
    //checks if book object exisits in library and if book is checked out(flase), if condidtions are met changes checkedOut to true and assigns a string to patron
    public void checkOutBook(String isbn, String patronName) {
        Book book = searchBooksByISBN(isbn);
        
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        
        if (book.isCheckedOut()) {
            System.out.println("Book is already checked out.");
            return;
        }
        
        book.setCheckedOut(true);
        book.setPatron(patronName);
        System.out.println("Book checked out successfully.");
    }
    
    //checks if book object exisits in library and if book isn't checked out(true), if condidtions are met changes checkedOut to false and assigns an emprty value to patron
    public void returnBook(String isbn) {
        Book book = searchBooksByISBN(isbn);
        
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        
        if (!book.isCheckedOut()) {
            System.out.println("Book is not checked out.");
            return;
        }
        
        book.setCheckedOut(false);
        book.setPatron("");
        System.out.println("Book returned successfully.");
    }
    
    //iterates over the list of books, assigning value to patron and checked out if conditions are met, and printing out contents in given form
    private void print(List<Book> books){
        for (Book book : books) {
            String checkedOutString = book.isCheckedOut() ? "Yes (" + book.getPatron() + ")" : "No";
            System.out.printf("%-30s %-30s %-20s %s\n", book.getTitle(), book.getAuthor(), book.getIsbn(), checkedOutString);
        }
    }


    //takes given file(filePath) and splits each line by ", " then assigns each piece to title, author, and isbn variables in new book objects
    //adding each book to the list item "books"
    public void importBooksFromFile(String filePath){
      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
          String[] inputArray = line.split(", ");
          Book book = new Book(inputArray[0], inputArray[1],inputArray[2]);
          this.books.add(book);
          System.out.println("Successfully added to library");
        }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }
}