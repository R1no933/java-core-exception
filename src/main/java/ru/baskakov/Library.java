package ru.baskakov;

import ru.baskakov.exception.ItemNotFoundException;
import ru.baskakov.exception.NotAvailableCopiesException;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> catalog = new ArrayList<Book>();

    public void addBook(String title, String author, int availableCopies) {
        catalog.add(new Book(title, author, availableCopies));
    }

    public void takeBook(String title) throws NotAvailableCopiesException {
        for (Book book : catalog) {
            if (book.getTitle().equals(title)) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
            }
        }
    }

    public void returnBook(String title) throws ItemNotFoundException {
        for (Book book : catalog) {
            if (book.getTitle().equals(title)) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
            }
        }
    }

    public List<Book> getAllBooks() {
        if (catalog.size() == 0) {
            System.out.println("Каталог пуст. Сперва добавьте книги");
        }

        return catalog;
    }
}