package com.example.userservice.utils.exceptions.annotations;

import com.example.userservice.utils.exceptions.RequestException;

public class RequiredParameterIsEmptyException extends RequestException {
    private final String paramName;

    public RequiredParameterIsEmptyException(String paramName) {
        this.paramName = paramName;
    }


    @Override
    public String getCode() {
        return "11";
    }

    @Override
    public String getMessage() {
        return "Ooops, required parameter " + this.paramName + " was not provided";
    }
}
