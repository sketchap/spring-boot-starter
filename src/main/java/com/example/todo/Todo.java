package com.example.todo;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "title", "order", "completed"})
public class Todo {

    private int id;
    private boolean completed;
    private int order;
    private String title;

    // required for jackson
    public Todo(){}

    public Todo(String title) {
        this.title = title;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getOrder() {
        return order;
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
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", completed=" + completed +
                ", order=" + order +
                ", title='" + title + '\'' +
                '}';
    }
}
