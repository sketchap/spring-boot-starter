package com.example.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class TodoTest {

    ObjectMapper mapper = new ObjectMapper();

    private final static boolean COMPLETED = true;
    private final static boolean NOT_COMPLETED = false;

    @DataProvider
    public static Object[][] todos() {
        return new Object[][]{
            {"title",   null,       3, null, NOT_COMPLETED, NOT_COMPLETED,  new Todo(1, "title",    3, NOT_COMPLETED)},
            {"title",   "hurzel",   3, null, NOT_COMPLETED, NOT_COMPLETED,  new Todo(1, "hurzel",   3, NOT_COMPLETED)},
            {"title",   "hurzel",   5, null, NOT_COMPLETED, NOT_COMPLETED,  new Todo(1, "hurzel",   5, NOT_COMPLETED)},
            {"title",   "hurzel",   5, null, NOT_COMPLETED, COMPLETED,      new Todo(1, "hurzel",   5, COMPLETED)},
        };
    }

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

    @Test(dataProvider = "todos")
    public void shouldMergeTodos(String title, String newTitle, Integer order, Integer newOrder, Boolean completed, Boolean newCompleted, Todo expected) throws Exception {
        // given
        Todo todo = new Todo(1, title, order, completed);
        Todo newTodo = new Todo(1, newTitle, newOrder, newCompleted);

        // when
        Todo merged = todo.merge(newTodo);

        // then
        assertThat(merged, is(expected));
    }
}