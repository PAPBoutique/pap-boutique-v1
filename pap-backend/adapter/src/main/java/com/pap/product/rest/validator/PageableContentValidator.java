package com.pap.product.rest.validator;

import com.pap.product.exception.ExceptionMessages;
import com.pap.product.exception.InvalidPageOrSizeException;

public class PageableContentValidator {
    public static void validate(int page , int size){
        if(page<0 || size>=6){
            throw new InvalidPageOrSizeException(ExceptionMessages.INVALIDPAGEORSIZE);
        }
    }
}
