package com.example.willmatebackend.service.User;

import com.example.willmatebackend.dto.PaginationDTO;
import com.example.willmatebackend.dto.ServerResponseDto;
import com.example.willmatebackend.dto.request.UserDTO;
import com.example.willmatebackend.repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public PaginationDTO<?> getUsers(int page, int size, String sortField, String sortDir, String keywordSearch) {
        return userRepository.getUserPagi(page,size,sortField,sortDir,keywordSearch);
    }

    @Override
    public ServerResponseDto createUsers(UserDTO userRequest) {
        return userRepository.createOrUpdateUser(userRequest);
    }
}
