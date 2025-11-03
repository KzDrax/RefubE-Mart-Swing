package styles;

import java.util.ArrayList;
import java.util.List;

// 🟩 Updated Cart class to store CartItem objects
public class Cart {
    private static final List<CartItem> items = new ArrayList<>();

    public static void addItem(CartItem newItem) {
        // 🟩 If item already exists, update quantity
        for (CartItem item : items) {
            if (item.getName().equals(newItem.getName())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public static List<CartItem> getItems() {
        return items;
    }

    public static void removeItem(CartItem item) {
        items.remove(item);
    }

    public static void clear() {
        items.clear();
    }
}
