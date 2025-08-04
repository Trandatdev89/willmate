package com.example.willmatebackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PaginationDTO<T>{
    private T data;
    private long totalElement;
    private int totalPage;
    private int currentPage;
    private int size;
}
