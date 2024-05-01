package main.repository;

import main.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static final Map<String, User> users = new HashMap<>();

    // Метод для добавления пользователя
    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    // Метод для получения пользователя по имени пользователя
    public static User getUserByUsername(String username) {
        return users.get(username);
    }
}
