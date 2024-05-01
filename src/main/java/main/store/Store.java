package main.store;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> products;

    public Store() {
        this.products = new ArrayList<>();
        // Инициализация списка товаров
        initializeProducts();
    }

    private void initializeProducts() {
        // Добавление товаров в магазин
        products.add(new Product(1, "Книга правил", 200));
        products.add(new Product(2, "Брелок", 300));
        products.add(new Product(3, "Комплект судейских карточек", 350));
        products.add(new Product(4, "Судейская пена", 500));
        products.add(new Product(5, "Свисток", 650));
        products.add(new Product(6, "Судейские флаги", 800));
        products.add(new Product(7, "Судейская форма", 1000));
    }

    public List<Product> getProducts() {
        return products;
    }

    // Другие методы для обработки покупок и т.д.
}
