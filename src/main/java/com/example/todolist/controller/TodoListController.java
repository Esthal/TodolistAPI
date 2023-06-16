package com.example.todolist.controller;

import com.example.todolist.entity.Todolist;
import com.example.todolist.entity.User;
import com.example.todolist.response.TodoResponse;
import com.example.todolist.service.TodolistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class TodoListController {
    private final TodolistService todolistService;

    @GetMapping("/list")
    public String show(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return todolistService.findAllByUser(user).toString();
    }
    @GetMapping("/remove/{id}")
    public boolean remove(
            @PathVariable Long id
    ){
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<Todolist> todoOptional = todolistService.findById(id, user.getId());
        if (todoOptional.isPresent()){
            todolistService.deleteById(id, user.getId());
            return true;
        }
        return false;
    }
    @GetMapping("/isDone/{id}")
    public boolean isDone(
            @PathVariable Long id,
            @RequestParam boolean isDone
    ){
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<Todolist> todoOptional = todolistService.findById(id, user.getId());
        if (todoOptional.isPresent()){
            Todolist todo = todoOptional.get();
            todo.setDone(isDone);
            todolistService.save(todo);
            return true;
        }
        return false;
    }
    @PostMapping("/update/{id}")
    public boolean update(
            @PathVariable Long id,
            @RequestBody
            TodoResponse response
    ){
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<Todolist> todoOptional = todolistService.findById(id, user.getId());
        if (todoOptional.isPresent()){
            Todolist todo = todoOptional.get();
            todo.setDateOfCase(response.getDate());
            todo.setTitleOfCase(response.getTitle());
            todo.setDescriptionOfCase(response.getDescription());
            todolistService.save(todo);
            return true;
        }
        return false;
    }


    @PostMapping ("/add")
    public boolean add(
            @RequestBody
            TodoResponse response)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Todolist todo = Todolist.builder()
                .user(user)
                .dateOfCase(response.getDate())
                .titleOfCase(response.getTitle())
                .descriptionOfCase(response.getDescription())
                .isDone(false)
                .build();
        todolistService.save(todo);
        return true;
    }
}
