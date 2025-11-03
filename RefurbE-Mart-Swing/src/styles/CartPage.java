package styles;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import styles.Styles;
import java.util.List;

// 🟩 Updated CartPage to include back button, remove button, and purchase button
public class CartPage extends JPanel {
    private JPanel cartItemsPanel; // 🟩 Updated: using JPanel instead of JTextArea for dynamic layout

    // 🟩 Added: reference to onNavigate for page navigation
    private Consumer<String> onNavigate;

    public CartPage(Consumer<String> onNavigate) {
        this.onNavigate = onNavigate; // 🟩 Added: store navigation reference
        setLayout(new BorderLayout());

        // 🟩 Added: Top panel (Back button + Title)
        JPanel topPanel = new JPanel(new BorderLayout());

        // 🟩 Added: Back button to return to Home
        JButton backButton = new JButton("Back");
        Styles.applyButtonStyle(backButton);
        backButton.addActionListener(e -> onNavigate.accept("Home"));
        topPanel.add(backButton, BorderLayout.WEST);

        // 🟩 Title label
        JLabel titleLabel = new JLabel("Your Shopping Cart", JLabel.CENTER);
        Styles.applyHeaderStyle(titleLabel);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // 🟩 Updated: Use scrollable panel for list of products
        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // 🟩 Added: Bottom buttons (Purchase + Continue Shopping)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton purchaseButton = new JButton("Purchase");
        Styles.applyButtonStyle(purchaseButton);
        purchaseButton.addActionListener(e -> handlePurchase());

        JButton continueShoppingButton = new JButton("Continue Shopping");
        Styles.applyButtonStyle(continueShoppingButton);
        continueShoppingButton.addActionListener(e -> onNavigate.accept("Products"));

        bottomPanel.add(purchaseButton);
        bottomPanel.add(continueShoppingButton);
        add(bottomPanel, BorderLayout.SOUTH);

        updateCartDisplay(); // 🟩 Refresh display on startup
    }

    // 🟩 Updated: Rebuild cart display each time
    public void updateCartDisplay() {
        cartItemsPanel.removeAll(); // Clear previous items

        List<CartItem> items = Cart.getItems();

        if (items.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty.", JLabel.CENTER);
            emptyLabel.setFont(Styles.BODY_FONT);
            cartItemsPanel.add(emptyLabel);
        } else {
            for (CartItem item : items) {
                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // 🟩 Left side: Product name and quantity
                JLabel itemLabel = new JLabel(item.getName() + " — ₱" + item.getPrice() + " x " + item.getQuantity());
                itemLabel.setFont(Styles.BODY_FONT);
                itemPanel.add(itemLabel, BorderLayout.CENTER);

                // 🟩 Right side: Remove button
                JButton removeButton = new JButton("Remove");
                Styles.applyButtonStyle(removeButton);
                removeButton.setBackground(new Color(220, 53, 69)); // Red button for delete
                removeButton.addActionListener(e -> {
                    Cart.removeItem(item);
                    updateCartDisplay();
                });
                itemPanel.add(removeButton, BorderLayout.EAST);

                cartItemsPanel.add(itemPanel);
            }
        }

        revalidate();
        repaint();
    }

    // 🟩 Added: Purchase handling (simulated checkout)
    private void handlePurchase() {
        if (Cart.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!");
            return;
        }

        double totalAmount = 0;
        for (CartItem item : Cart.getItems()) {
            totalAmount += item.getPrice() * item.getQuantity();
        }

        JOptionPane.showMessageDialog(this, "Purchase successful!\nTotal amount: ₱" + totalAmount);

        // 🟩 After purchase, clear cart
        Cart.clear();
        updateCartDisplay();
    }
}
