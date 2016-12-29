package com.example.todo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by stschapk on 17.12.16.
 */
@RestController
@RequestMapping(value = "/todos")
public class TodoController {

    @RequestMapping(method = RequestMethod.GET)
    public String listTodos(){
        return "// TODO: get all todos";
    }
}
