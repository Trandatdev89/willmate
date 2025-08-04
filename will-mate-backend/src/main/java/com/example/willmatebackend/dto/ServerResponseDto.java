package com.example.willmatebackend.dto;

import lombok.Getter;

@Getter
public class ServerResponseDto {

    public static final ServerResponseDto SUCCESS = new ServerResponseDto(ResponseCase.SUCCESS);
    public static final ServerResponseDto ERROR = new ServerResponseDto(ResponseCase.ERROR);


    private final ResponseCase status;
    private Object data;

    public ServerResponseDto(ResponseCase responseCase) {
         this.status = responseCase;
    }

    public ServerResponseDto(ResponseCase responseCase,Object data) {
        this.status = responseCase;
        this.data = data;
    }

    public static ServerResponseDto success(Object data) {
        return new ServerResponseDto(ResponseCase.SUCCESS, data);
    }

    public static ServerResponseDto error(Object data) {
        return new ServerResponseDto(ResponseCase.ERROR, data);
    }

    public static ServerResponseDto with(ResponseCase responseCase) {
        return new ServerResponseDto(responseCase);
    }

    public static ServerResponseDto with(ResponseCase responseCase, Object data) {
        return new ServerResponseDto(responseCase, data);
    }
}
