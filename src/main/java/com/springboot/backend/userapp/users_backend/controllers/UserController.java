package com.springboot.backend.userapp.users_backend.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import com.springboot.backend.userapp.users_backend.entities.User;
import com.springboot.backend.userapp.users_backend.models.UserRequest;
import com.springboot.backend.userapp.users_backend.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins={"http://localhost:4200"}, originPatterns = {"*"}) // Change the origin to the frontend url
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public List<User> list(){
        return this.service.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<User> listPageable(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 4);
        return this.service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "The user was not found by id: " + id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if (result.hasErrors()) {
            if (result.getFieldError() != null) {
               return validation(result);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in the data sent");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserRequest user, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()) {
            if (result.getFieldError() != null) {
               return validation(result);
            }
        }
        Optional<User> userOptional = service.update(user, id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
           result.getFieldErrors().forEach(err -> {
               errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
           });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
