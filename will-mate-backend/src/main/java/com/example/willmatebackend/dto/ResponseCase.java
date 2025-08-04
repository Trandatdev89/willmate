package com.example.willmatebackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCase {
    SUCCESS(1000,"SUCCESS"),

    NOTFOUND(1404,"CANNOT FOUND"),
    ERROR(4,"ERROR");

    private final int code;
    private final String message;

    ResponseCase(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
