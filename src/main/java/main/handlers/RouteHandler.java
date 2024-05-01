package main.handlers;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class RouteHandler {

    // Метод для обработки запроса на получение списка вопросов
    public static String handleGetQuestions(Request request, Response response) {
        // Здесь может быть логика для получения списка вопросов из базы данных или другого источника данных
        // В этом примере просто возвращаем статический список вопросов для демонстрации
        String[] questions = {"Вопрос 1", "Вопрос 2", "Вопрос 3"};

        // Преобразуем массив вопросов в формат JSON и отправляем его в ответе
        Gson gson = new Gson();
        return gson.toJson(questions);
    }

    // Метод для обработки запроса на отправку ответа на вопрос
    public static String handleAnswerQuestion(Request request, Response response) {
        // Получаем данные из тела запроса (например, ответ пользователя)
        String answer = request.body();

        // Здесь может быть логика для проверки ответа и сохранения результатов
        // В этом примере просто возвращаем сообщение об успешном получении ответа для демонстрации
        return "Ответ успешно получен: " + answer;
    }
}
