package com.example.willmatebackend.controller;

import com.example.willmatebackend.dto.ServerResponseDto;
import com.example.willmatebackend.dto.request.UserDTO;
import com.example.willmatebackend.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdTime", required = false) String sortField,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam String keywordSearch){

        return ResponseEntity.ok().body(userService.getUsers(page,size,sortField,sortDir,keywordSearch));
    }

    @PostMapping
    public ResponseEntity<?> createUsers(@RequestBody UserDTO userRequest){
        return ResponseEntity.ok().body(userService.createUsers(userRequest));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ServerResponseDto> getUserById(@PathVariable String id){
        return null;
    }

}
