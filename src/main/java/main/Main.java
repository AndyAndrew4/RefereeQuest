package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import main.model.AuthHandler;
import main.model.PurchaseResponse;
import main.model.RefereeAccessory;
import main.store.Product;
import main.store.Store;
import spark.Session;


import java.util.List;
import java.util.ArrayList;

import static spark.Spark.*;

public class Main {
    private static final Gson gson = new Gson(); // Создаем экземпляр Gson

    public static void main(String[] args) {

        Store store = new Store();
        // Статическая инициализация приложения Spark
        port(8080); // Установка порта, на котором будет работать сервер

        // Маршрут для регистрации нового пользователя
        post("/register", AuthHandler::register);

        // Маршрут для входа пользователя
        post("/login", AuthHandler::login);

        ObjectMapper objectMapper = new ObjectMapper();

        get("/store", (req, res) -> {
            List<Product> products = store.getProducts();
            return objectMapper.writeValueAsString(products); // Преобразуем список в JSON
        });

        // Маршрут для защищенной страницы (пример)
        get("/secured", (req, res) -> {
            Session session = req.session(false); // Получаем текущий сеанс, не создавая нового
            if (session != null && session.attribute("loggedIn") != null) {
                return "Это защищенный маршрут!";
            } else {
                res.status(401); // Неавторизованный доступ
                return "Доступ запрещен! Войдите в систему.";
            }
        });

        // Маршрут для получения списка аксессуаров и их цен
        get("/accessories", (request, response) -> {
            List<RefereeAccessory> accessories = new ArrayList<>();
            // Добавляем каждый аксессуар в список
            accessories.add(new RefereeAccessory("Книга правил", 200));
            accessories.add(new RefereeAccessory("Брелок", 300));
            accessories.add(new RefereeAccessory("Комплект судейских карточек", 350));
            accessories.add(new RefereeAccessory("Судейская пена", 500));
            accessories.add(new RefereeAccessory("Свисток", 650));
            accessories.add(new RefereeAccessory("Судейские флаги", 800));
            accessories.add(new RefereeAccessory("Судейская форма", 1000));
            // Преобразуем список аксессуаров в JSON и отправляем его клиенту
            return gson.toJson(accessories);
        });

        // Маршрут для обмена монеток на аксессуар
        post("/purchase", (request, response) -> {
            // Получаем данные о выбранном аксессуаре и количестве монеток пользователя из запроса
            String accessoryName = request.queryParams("name");
            int coins = Integer.parseInt(request.queryParams("coins"));
            // Определяем цену выбранного аксессуара
            int price = 0;
            switch (accessoryName) {
                case "Книга правил":
                    price = 200;
                    break;
                case "Брелок":
                    price = 300;
                    break;
                case "Комплект судейских карточек":
                    price = 350;
                    break;
                case "Судейская пена":
                    price = 500;
                    break;
                case "Свисток":
                    price = 650;
                    break;
                case "Судейские флаги":
                    price = 800;
                    break;
                case "Судейская форма":
                    price = 1000;
                    break;
            }
            // Если пользователь имеет достаточно монеток, обмениваем их на аксессуар
            if (coins >= price) {
                // Вычитаем цену аксессуара из количества монеток пользователя
                int remainingCoins = coins - price;
                // Отправляем ответ клиенту с сообщением об успешной покупке и оставшимся количеством монеток
                return gson.toJson(new PurchaseResponse(true, "Покупка успешно совершена", remainingCoins));
            } else {
                // Если у пользователя недостаточно монеток, отправляем сообщение об ошибке
                return gson.toJson(new PurchaseResponse(false, "Недостаточно монеток для покупки", coins));
            }
        });
    }
}
