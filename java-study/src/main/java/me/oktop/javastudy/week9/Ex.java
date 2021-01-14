package me.oktop.javastudy.week9;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ex {

    public void tryCatchException() {
        Post post = new Post();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void throwsException() throws JsonProcessingException {
        Post post = new Post();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(post);
    }

    public static void main(String[] args) {
        Ex ex = new Ex();
        Post post = null;
        System.out.println(post.getName());
    }
}
