package com.pap.product.exception;

public class InvalidPageOrSizeException extends IllegalArgumentException{
    public InvalidPageOrSizeException(String message){
        super(message);
    }
}
