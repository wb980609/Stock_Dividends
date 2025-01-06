package com.dayone.exception;

public abstract class AbstractException extends RuntimeException {

    abstract public int getStatusCode();

    @Override
    abstract public String getMessage();

}
