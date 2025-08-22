package ru.baskakov.exception;

public class NotAvailableCopiesException extends Exception{
    public NotAvailableCopiesException(){
        super();
    }

    public NotAvailableCopiesException(String message){
        super(message);
    }
}
