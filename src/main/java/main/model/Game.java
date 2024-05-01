package main.model;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private Player player;
    private List<Level> levels;
    private Level currentLevel;

    public Game() {
        this.player = new Player();
        this.levels = new ArrayList<>();
        this.currentLevel = null;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public void startNewGame() {
        player = new Player();
        currentLevel = levels.get(0);
        player.setCurrentLevel(1);
        player.setCurrentQuestionIndex(0);
    }

    public boolean answerQuestion(int selectedOptionIndex) {
        if (currentLevel != null) {
            Question currentQuestion = currentLevel.getQuestions().get(player.getCurrentQuestionIndex());
            if (selectedOptionIndex == currentQuestion.getCorrectOptionIndex()) {
                player.addCoins(3); // За каждый правильный ответ добавляем 3 монеты
                player.setCurrentQuestionIndex(player.getCurrentQuestionIndex() + 1);
                if (player.getCurrentQuestionIndex() >= currentLevel.getQuestions().size()) {
                    // Если ответили на все вопросы уровня, переходим к следующему уровню
                    player.setCurrentLevel(player.getCurrentLevel() + 1);
                    if (player.getCurrentLevel() < levels.size()) {
                        currentLevel = levels.get(player.getCurrentLevel());
                        player.setCurrentQuestionIndex(0);
                    } else {
                        // Игра завершена, выигрышная ситуация
                        return true;
                    }
                }
                // Вернем true, чтобы указать, что ответ был правильным
                return true;
            }
        }
        // Вернем false, чтобы указать, что ответ был неправильным или произошла ошибка
        return false;
    }

    // Методы для получения информации о текущем состоянии игры
    public Player getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
