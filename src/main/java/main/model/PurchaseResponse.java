package main.model;

public class PurchaseResponse {
    private boolean success;
    private String message;
    private int remainingCoins;

    public PurchaseResponse(boolean success, String message, int remainingCoins) {
        this.success = success;
        this.message = message;
        this.remainingCoins = remainingCoins;
    }

    // Геттеры и сеттеры для атрибутов
}
