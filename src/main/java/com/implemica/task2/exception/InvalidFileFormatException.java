package com.implemica.task2.exception;

/**
 * Created by Евгений on 28.06.2017.
 */
public class InvalidFileFormatException extends Exception {

    public InvalidFileFormatException() {
        super();
    }

    public InvalidFileFormatException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "File has invalid data.";
    }
}
