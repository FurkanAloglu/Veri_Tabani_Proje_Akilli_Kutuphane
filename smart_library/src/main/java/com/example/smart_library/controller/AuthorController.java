package com.example.smart_library.controller;

import com.example.smart_library.model.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.smart_library.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import lombok.*;

@RestController
@RequestMapping("/api/authors")

@RequiredArgsConstructor

public class AuthorController{

    private final AuthorService authorService;



}
