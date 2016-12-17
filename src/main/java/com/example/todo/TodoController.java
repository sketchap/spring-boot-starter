package com.example.todo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by stschapk on 17.12.16.
 */
@RestController
public class TodoController {

    @RequestMapping("/")
    public String home(){
        return "Hello Spring! ;)";
    }
}
