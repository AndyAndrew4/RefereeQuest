package main.handlers;

import com.google.gson.Gson;
import main.model.User;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class UserHandler {
    private static final Gson gson = new Gson();
    private static final Map<String, User> users = new HashMap<>();

    public static Object handleGetUser(Request request, Response response) {
        // Обработка GET запроса на получение информации о пользователе
        String username = request.params("username");
        User user = users.get(username);
        if (user != null) {
            return gson.toJson(user);
        } else {
            response.status(404);
            return "User not found!";
        }
    }

    public static Object handlePostUser(Request request, Response response) {
        // Обработка POST запроса на добавление нового пользователя
        User user = gson.fromJson(request.body(), User.class);
        users.put(user.getUsername(), user);
        return "User added successfully!";
    }
}
