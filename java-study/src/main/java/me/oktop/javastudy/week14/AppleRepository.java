package me.oktop.javastudy.week14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppleRepository {

    private Map<Long, Apple> map = new HashMap<>();

    // create, update
    public void save(Apple apple) {
        map.put(apple.getId(), apple);
    }

    // read
    public List<Apple> findAll() {
        return new ArrayList<>(map.values());
    }

    // delete
    public void delete(long id) {
        map.remove(id);
    }

}
