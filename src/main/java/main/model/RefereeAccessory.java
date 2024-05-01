package main.model;

public class RefereeAccessory {
    private String name;
    private int price;
    private String description;

    public RefereeAccessory(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public RefereeAccessory(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Геттеры и сеттеры для атрибутов
}
