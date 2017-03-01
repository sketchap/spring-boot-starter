package com.example.todo;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/")
public class TodoController {

    Set<Todo> todos = new HashSet<>();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<ResourceWithUrl>> listTodos(){
        return ResponseEntity.ok().body(todos.stream().map(this::createResourceWithUrl).collect(toList()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<ResourceWithUrl> getTodoById(@PathVariable String id){
        Optional<Todo> todo = findTodoById(id);
        if(!todo.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createResourceWithUrl(todo.get()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResourceWithUrl> createTodo(@RequestBody Todo todo){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        todo.setId(todos.size() + 1);
        todos.add(todo);
        return ResponseEntity.ok().headers(httpHeaders).body(createResourceWithUrl(todo));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "{id}")
    public ResponseEntity<ResourceWithUrl> updateTitle(@PathVariable String id, @RequestBody Todo todo){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Optional<Todo> todoOptional = findTodoById(id); // brauch ich die Prüfung bei PATCH?
        if(!todoOptional.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Todo currentTodo = todoOptional.get();
        todo.setOrder(currentTodo.getOrder());
        todo.setCompleted(currentTodo.isCompleted());

        todos.remove(currentTodo);
        todos.add(todo);
        return ResponseEntity.ok().headers(httpHeaders).body(createResourceWithUrl(todo));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllTodos() {
        todos.clear();
    }

    private Optional<Todo> findTodoById(String id){
        return todos.stream().filter(t -> t.getId() == Integer.valueOf(id)).findFirst();
    }

    private ResourceWithUrl<Todo> createResourceWithUrl(Todo todo) {
        return new ResourceWithUrl<>(todo, createHrefWithSelfRel(todo));
    }

    private String createHrefWithSelfRel(Todo todo) {
        return linkTo(methodOn(TodoController.class).getTodoById(String.valueOf(todo.getId()))).withSelfRel().getHref();
    }
}
