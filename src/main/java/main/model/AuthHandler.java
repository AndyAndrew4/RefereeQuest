package main.model;

import main.repository.UserRepository;
import spark.Request;
import spark.Response;

public class AuthHandler {
    public static String register(Request request, Response response) {
        String username = request.queryParams("username");
        String password = request.queryParams("password");

        // Проверяем, не существует ли уже пользователь с таким именем
        if (UserRepository.getUserByUsername(username) != null) {
            response.status(400); // Код ошибки 400 (неверный запрос)
            return "Пользователь с таким именем уже существует!";
        }

        // Создаем нового пользователя и добавляем его в хранилище
        User newUser = new User(username, password);
        UserRepository.addUser(newUser);

        return "Пользователь успешно зарегистрирован!";
    }

    public static String login(Request request, Response response) {
        String username = request.queryParams("username");
        String password = request.queryParams("password");

        // Получаем пользователя из хранилища
        User user = UserRepository.getUserByUsername(username);

        // Проверяем, существует ли пользователь и совпадает ли его пароль
        if (user != null && user.getPassword().equals(password)) {
            // Вход выполнен успешно
            return "Вход выполнен успешно!";
        } else {
            // Неверные учетные данные
            response.status(401); // Код ошибки 401 (неавторизовано)
            return "Неверное имя пользователя или пароль!";
        }
    }
}
