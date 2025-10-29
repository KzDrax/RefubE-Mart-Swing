package styles;

import java.util.*;

public class Cart {
    public static class CartItem {
        public String name;
        public int price;
        public int quantity;
        public CartItem(String name, int price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

    private static Map<String, CartItem> items = new LinkedHashMap<>();

    public static void addItem(String name, int price, int quantity) {
        if (items.containsKey(name)) {
            CartItem existing = items.get(name);
            existing.quantity += quantity;
        } else {
            items.put(name, new CartItem(name, price, quantity));
        }
    }

    public static Map<String, CartItem> getItems() {
        return items;
    }
}
