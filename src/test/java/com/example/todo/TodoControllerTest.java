package com.example.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
public class TodoControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType());

    @Test
    public void listTodos() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // when // then
        this.mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(mapper.writeValueAsString(new HashSet<Todo>())));
    }

    @Test
    public void shouldReturnTodoWhichWasPosted() throws Exception {
        // given
        Todo todo = new Todo("test");
        ObjectMapper mapper = new ObjectMapper();

        // when // then
        this.mockMvc
                .perform(post("/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(todo)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void shouldDeleteAllTodos() throws Exception {
        // when // then
        this.mockMvc
                .perform(delete("/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddTodoToListOfTodosAtTheRootUrl() throws Exception {
        // given
        Todo todo = new Todo("test");
        ObjectMapper mapper = new ObjectMapper();
        // controller erzeugen und methode aufrufen oder nur mit api interagieren?
        // when
        this.mockMvc
            .perform(post("/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(todo)))
            .andExpect(status().isOk());

        HashSet<Todo> todos = new HashSet<>();
        todo.setId(1);
        todos.add(todo);
        this.mockMvc
            .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(todos)));
    }

    @Test
    public void shouldGetTodoById() throws Exception {
        // given
        // TODO prepare controller / model

        // when
        this.mockMvc
                .perform(get("/1"))
                .andExpect(status().isNotFound());

    }
}
