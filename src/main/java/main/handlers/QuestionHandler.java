package main.handlers;

import com.google.gson.Gson;
import main.database.Database;
import main.model.Question;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionHandler {
    private static final Gson gson = new Gson();
    private static final List<Question> questions = new ArrayList<>();

    public static Object handleGetQuestions(Request request, Response response) {
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM questions");

            List<Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                Question question = new Question(resultSet.getString("questionText"),
                        new String[]{resultSet.getString("option1"),
                                resultSet.getString("option2"),
                                resultSet.getString("option3"),
                                resultSet.getString("option4")},
                        resultSet.getInt("correctOptionIndex"));
            }

            return gson.toJson(questions);
        } catch (SQLException e) {
            e.printStackTrace();
            response.status(500);
            return "Internal server error";
        }
    }

    public static Object handlePostQuestion(Request request, Response response) {
        // Обработка POST запроса на добавление нового вопроса
        Question question = gson.fromJson(request.body(), Question.class);
        questions.add(question);
        return "Question added successfully!";
    }
}
