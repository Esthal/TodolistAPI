package com.example.todolist.repository;

import com.example.todolist.entity.Todolist;
import com.example.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TodolistRepository extends JpaRepository<Todolist, Long> {

    List<Todolist> findAllByUser(User user);

    @Query(nativeQuery = true, value = "SELECT * FROM todolist WHERE"+
            " id = :todo_id" +
            " AND user_id = :user_id")
    Optional<Todolist> findById(@Param("todo_id") Long todo_id, @Param("user_id") Long user_id);


    @Query(nativeQuery = true, value = "DELETE FROM todolist WHERE"+
            " id = :todo_id" +
            " AND user_id = :user_id")
    void deleteTodoById(@Param("todo_id") Long todo_id, @Param("user_id") Long user_id);

}
