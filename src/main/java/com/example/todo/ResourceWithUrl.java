package com.example.todo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResourceWithUrl<T> {

    private T content;

    private String url;

    public ResourceWithUrl() {
    }

    public ResourceWithUrl(T content, String url) {
        this.content = content;
        this.url = url;
    }

    @JsonUnwrapped
    public T getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
