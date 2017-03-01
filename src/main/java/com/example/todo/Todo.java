package com.example.todo;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

import static java.util.Objects.nonNull;

@JsonPropertyOrder({"id", "title", "order", "completed"})
public class Todo {

    private int id;
    private Boolean completed;
    private Integer order;
    private String title;

    // required for jackson
    public Todo(){}

    public Todo(String title) {
        this.title = title;
    }

    public Todo(int id, String title, Integer order, Boolean completed){
        this.id = id;
        this.title = title;
        this.order = order;
        this.completed = completed;
    }

    public Todo merge(Todo todo){
        Todo newTodo = new Todo(todo.getTitle());
        newTodo.setId(todo.getId());
        newTodo.setCompleted(nonNull(todo.completed) ? todo.isCompleted() : this.isCompleted());
        newTodo.setTitle(nonNull(todo.title) ? todo.getTitle() : this.getTitle());
        newTodo.setOrder(nonNull(todo.order) ? todo.getOrder() : this.getOrder());
        return newTodo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public boolean isCompleted() {
        return nonNull(completed) ? completed : false;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getOrder() {
        return nonNull(order) ? order : 0;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id &&
                Objects.equals(this.isCompleted(), todo.isCompleted()) &&
                Objects.equals(this.getOrder(), todo.getOrder()) &&
                Objects.equals(this.getTitle(), todo.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, completed, order, title);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", completed=" + completed +
                ", order=" + order +
                ", title='" + title + '\'' +
                '}';
    }
}
