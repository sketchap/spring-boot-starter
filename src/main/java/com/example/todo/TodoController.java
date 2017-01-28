package com.example.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/")
public class TodoController {

    Set<ResourceWithUrl> todos = new HashSet<>();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<ResourceWithUrl>> listTodos(){
        return ResponseEntity.ok().body(todos);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResourceWithUrl> createTodo(@RequestBody Todo todo){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        todo.setId(todos.size() + 1);
        ResourceWithUrl<Todo> todoWithUrl = createResourceWithUrl(todo);
        todos.add(todoWithUrl);
        return ResponseEntity.ok().headers(httpHeaders).body(todoWithUrl);
    }

    private ResourceWithUrl<Todo> createResourceWithUrl(Todo todo) {
        return new ResourceWithUrl<>(todo, createUrl(todo));
    }

    private String createUrl(Todo todo) {
        return String.valueOf(todo.getId());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllTodos() {
        todos.clear();
    }
}
