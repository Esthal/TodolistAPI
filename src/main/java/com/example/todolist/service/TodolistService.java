package com.example.todolist.service;

import com.example.todolist.entity.Todolist;
import com.example.todolist.entity.User;
import com.example.todolist.repository.TodolistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodolistService {
    public final TodolistRepository todolistRepository;

    public List<Todolist> findAllByUser(User user){
        return todolistRepository.findAllByUser(user);
    }
    public Optional<Todolist> findById(Long todoId, Long userId){
        return todolistRepository.findById(todoId, userId);
    }
    public void deleteById(Long todoId, Long userId){
        todolistRepository.deleteTodoById(todoId, userId);
    }
    public void save(Todolist todolist){
        todolistRepository.save(todolist);
    }

}
