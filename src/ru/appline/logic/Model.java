package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Name1", "Surname1", 111111));
        model.put(2, new User("Name2", "Surname2", 222222));
        model.put(3, new User("Name3", "Surname3", 333333));
    }

    public void add(User user, int id) {
        model.put(id, user);
    }

    public void delete(int id) {
        model.remove(id);
    }

    public void put(int id) {
        model.remove(id);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }
}
