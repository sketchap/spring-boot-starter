package com.example.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class TodoTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldSerializeCorrectly() throws Exception {
        // given
        Todo todo = new Todo("ich bin ein test");
        // when
        String todoJson = mapper.writeValueAsString(todo);
        // then
        assertThat(todoJson, is("{\"id\":0,\"title\":\"ich bin ein test\",\"order\":0,\"completed\":false}"));
    }

    @Test
    public void shouldHandleHashSetsProperly() throws Exception {
        // given
        Set<Todo> todos = new HashSet<>();
        todos.add(new Todo("test"));
        // when
        String todosArray = mapper.writeValueAsString(todos);
        // then
        assertThat(todosArray, is("[{\"id\":0,\"title\":\"test\",\"order\":0,\"completed\":false}]"));
    }

    @Test
    public void shouldDeserializeCorrectly() throws Exception {


    }
}