package main.model;

public class Player {
    private int coins;
    private int currentLevel;
    private int currentQuestionIndex;

    public Player() {
        this.coins = 0;
        this.currentLevel = 0;
        this.currentQuestionIndex = 0;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int level) {
        currentLevel = level;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int index) {
        currentQuestionIndex = index;
    }
}
