package ru.baskakov;

import ru.baskakov.exception.ItemNotFoundException;
import ru.baskakov.exception.NotAvailableCopiesException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookCatalog {
    private static final String MAIN_MENU = """
            ===============================================================================================
            Добро пожаловать в менеджер книжного каталога.
            ===============================================================================================
            Выберите необходимый пункт меню:
            1. Вывести каталог
            2. Добавить объект
            3. Выдать объект
            4. Вернуть объект
            5. Выйти из приложения
            Выберите дальнейшее действие:
            """;

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int choice;
        while (!exit) {
            System.out.println(MAIN_MENU);
            choice = choiceMenuItem();
            switch (choice) {
                case 1 -> System.out.println(library.getAllBooks().toString());
                case 2 -> {
                    Book book = createBook();
                    library.addBook(book.getTitle(), book.getAuthor(), book.getAvailableCopies());
                }
                case 3 -> {
                    System.out.println("Введите название книги:");
                    try {
                        String title = scanner.nextLine();
                        library.takeBook(title);
                    } catch (InputMismatchException exception) {
                        System.out.println("Вы ввели некорректные данные, название - строка!");
                    } catch (NotAvailableCopiesException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("Введите название книги:");
                    try {
                        String title = scanner.nextLine();
                        library.returnBook(title);
                    } catch (InputMismatchException exception) {
                        System.out.println("Вы ввели некорректные данные, название - строка!");
                    } catch (ItemNotFoundException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
                case 5 -> {
                    exit = true;
                    System.out.println("Спасибо за выбор нашего менеджера каталога! Хорошего дня!");
                }
                default -> System.out.println("Введена некорректная цифра! Введите только цифру пункта меню от 1 до 5");
            }
        }
    }

    private static int choiceMenuItem() {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Введите только цифру пункта меню от 1 до 5");
        }
        return choice;
    }

    private static Book createBook() {
        Scanner scanner = new Scanner(System.in);
        String bookTitle = null;
        String bookAuthor = null;
        int bookCopies = 0;
        try {
            System.out.println("Введите название книги");
            bookTitle = scanner.nextLine();
            System.out.println("Введите автора книги:");
            bookAuthor = scanner.nextLine();
            System.out.println("Введите количество копий:");
            bookCopies = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Введены некорректные данные! Название и автор - строчные значения, число копий - целочисленное!");
        }
        return new Book(bookTitle, bookAuthor, bookCopies);
    }


}