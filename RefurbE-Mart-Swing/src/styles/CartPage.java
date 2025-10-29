package styles;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class CartPage extends JPanel {
    private JPanel cartItemsPanel;

    public CartPage(java.util.function.Consumer<String> onNavigate) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Your Cart", JLabel.CENTER);
        Styles.applyHeaderStyle(title);
        add(title, BorderLayout.NORTH);

        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(cartItemsPanel), BorderLayout.CENTER);
    }

    // --- [UPDATED] --- Refresh display to include name, qty, and ₱ total
    public void updateCartDisplay() {
        cartItemsPanel.removeAll();

        if (Cart.getItems().isEmpty()) {
            cartItemsPanel.add(new JLabel("Your cart is empty"));
        } else {
            for (Map.Entry<String, Cart.CartItem> entry : Cart.getItems().entrySet()) {
                Cart.CartItem item = entry.getValue();
                JLabel label = new JLabel(item.name + " — ₱" + item.price + " × " + item.quantity + " = ₱" + (item.price * item.quantity));
                cartItemsPanel.add(label);
            }
        }

        revalidate();
        repaint();
    }
}
