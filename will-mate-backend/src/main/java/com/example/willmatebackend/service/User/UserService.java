package com.example.willmatebackend.service.User;

import com.example.willmatebackend.dto.PaginationDTO;
import com.example.willmatebackend.dto.ServerResponseDto;
import com.example.willmatebackend.dto.request.UserDTO;

public interface UserService {
    PaginationDTO<?> getUsers(int page, int size, String sortField, String sortDir, String keywordSearch);
    ServerResponseDto createUsers(UserDTO userRequest);
}
