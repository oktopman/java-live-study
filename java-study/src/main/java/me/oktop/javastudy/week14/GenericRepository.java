package me.oktop.javastudy.week14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericRepository<E extends Fruit<K>, K> {

    private Map<K, E> map = new HashMap<>();

    // create, update
    public void save(E fruit) {
        map.put(fruit.getId(), fruit);
    }

    // read
    public List<E> findAll() {
        return new ArrayList<>(map.values());
    }

    // delete
    public void delete(K id) {
        map.remove(id);
    }
}
