package com.example.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class ResourceWithUrlTest {


    @Test
    public void shouldSerializeCorrectly() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Todo todo = new Todo("hallo");
        String url = new String("id1");
        // when
        String resourceWithUrlJson = mapper.writeValueAsString(new ResourceWithUrl<Todo>(todo, url));
        // then
        assertThat(resourceWithUrlJson, is("{\"id\":0,\"title\":\"hallo\",\"order\":0,\"completed\":false,\"url\":\"id1\"}"));

    }
}