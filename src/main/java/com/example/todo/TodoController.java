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

    Set<Todo> todos = new HashSet<>();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Todo>> listTodos(){
        return ResponseEntity.ok().body(todos);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createTodo(@RequestBody String todo){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return ResponseEntity.ok().headers(httpHeaders).body(todo);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllTodos() {
        todos.clear();
    }
}
