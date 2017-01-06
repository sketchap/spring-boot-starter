package com.example.todo;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class TodoController {

    @RequestMapping(method = RequestMethod.GET)
    public String listTodos(){
        return "TODO";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createTodo(@RequestBody String todo){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok().headers(httpHeaders).body(todo);
    }
}
