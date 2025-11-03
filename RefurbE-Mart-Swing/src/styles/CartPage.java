package styles;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import styles.Styles;
import java.util.List;

public class CartPage extends JPanel {
    private JPanel cartItemsPanel;

    private Consumer<String> onNavigate;

    public CartPage(Consumer<String> onNavigate) {
        this.onNavigate = onNavigate;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back");
        Styles.applyButtonStyle(backButton);
        backButton.addActionListener(e -> onNavigate.accept("Home"));
        topPanel.add(backButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Your Shopping Cart", JLabel.CENTER);
        Styles.applyHeaderStyle(titleLabel);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);
        add(scrollPane, BorderLayout.CENTER);

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

        updateCartDisplay();
    }

    public void updateCartDisplay() {
        cartItemsPanel.removeAll();

        List<CartItem> items = Cart.getItems();

        if (items.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty.", JLabel.CENTER);
            emptyLabel.setFont(Styles.BODY_FONT);
            cartItemsPanel.add(emptyLabel);
        } else {
            for (CartItem item : items) {
                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel itemLabel = new JLabel(item.getName() + " — ₱" + item.getPrice() + " x " + item.getQuantity());
                itemLabel.setFont(Styles.BODY_FONT);
                itemPanel.add(itemLabel, BorderLayout.CENTER);

                JButton removeButton = new JButton("Remove");
                Styles.applyButtonStyle(removeButton);
                removeButton.setBackground(new Color(220, 53, 69)); 
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

        Cart.clear();
        updateCartDisplay();
    }
}
