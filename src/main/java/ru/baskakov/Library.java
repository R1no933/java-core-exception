package ru.baskakov;

import ru.baskakov.exception.ItemNotFoundException;
import ru.baskakov.exception.NotAvailableCopiesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private static final String EMPTY_CATALOG = "Каталог пуст! Сперва добваьте книгу!";
    private static final String EMPTY_ITEM = "Нет данной книги в каталоге, сперва нужно ее добавить!";
    private List<Book> catalog = new ArrayList<Book>();

    public void addBook(String title, String author, int availableCopies) {
        catalog.add(new Book(title, author, availableCopies));
    }

    public void takeBook(String title) throws NotAvailableCopiesException {
        boolean found = false;
        if (catalog.isEmpty()) {
            throw new NotAvailableCopiesException(EMPTY_CATALOG);
        }

        for (Book book : catalog) {
            if (book.getTitle().equals(title)) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                found = true;
            }
        }
        if (!found) {
            throw new NotAvailableCopiesException(EMPTY_ITEM);
        }
    }

    public void returnBook(String title) throws ItemNotFoundException {
        boolean found = false;
        if (catalog.isEmpty()) {
            throw new ItemNotFoundException(EMPTY_CATALOG);
        }

        for (Book book : catalog) {
            if (book.getTitle().equals(title)) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                found = true;
            }
        }
        if (!found) {
            throw new ItemNotFoundException(EMPTY_ITEM);
        }
    }

    public List<Book> getAllBooks() {
        if (catalog.isEmpty()) {
            System.out.println(EMPTY_CATALOG);
        }

        return catalog;
    }
}