package styles;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import org.json.JSONObject;
import styles.Cart;

public class ProductDetailsPage extends JPanel {
    private static JSONObject selectedProduct;
    private JSpinner quantitySpinner;
    private JLabel stockLabel;

    // --- [NEW] --- Used to store the selected product data
    public static void setSelectedProduct(JSONObject product) {
        selectedProduct = product;
    }

    public ProductDetailsPage(Consumer<String> onNavigate) {
        setLayout(new BorderLayout());

        // --- [NEW] --- Back button
        JButton backButton = new JButton("Back to Products");
        Styles.applyButtonStyle(backButton);
        backButton.addActionListener(e -> onNavigate.accept("Products"));
        add(backButton, BorderLayout.NORTH);

        // --- [NEW] --- Main product info area
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel();
        JLabel priceLabel = new JLabel();
        JLabel descLabel = new JLabel();
        stockLabel = new JLabel();

        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        descLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        contentPanel.add(nameLabel);
        contentPanel.add(priceLabel);
        contentPanel.add(descLabel);
        contentPanel.add(stockLabel);

        // --- [NEW] --- Quantity selector
        JPanel qtyPanel = new JPanel();
        qtyPanel.add(new JLabel("Quantity: "));
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1, 1)); // default limits
        qtyPanel.add(quantitySpinner);
        contentPanel.add(qtyPanel);

        // --- [NEW] --- Buy button
        JButton buyButton = new JButton("Buy Now");
        Styles.applyButtonStyle(buyButton);
        contentPanel.add(buyButton);

        add(contentPanel, BorderLayout.CENTER);

        // --- [NEW] --- Populate UI when selectedProduct changes
        SwingUtilities.invokeLater(() -> {
            if (selectedProduct != null) {
                String name = selectedProduct.getString("name");
                int price = selectedProduct.getInt("price");
                int stock = selectedProduct.getInt("stock");
                String description = selectedProduct.getString("description");

                nameLabel.setText(name);
                priceLabel.setText("₱" + price);
                descLabel.setText(description);
                stockLabel.setText("Stock: " + stock);

                ((SpinnerNumberModel) quantitySpinner.getModel()).setMaximum(stock);
            }
        });

        // --- [NEW] --- Handle buying logic
        buyButton.addActionListener(e -> {
            if (selectedProduct == null) return;
            int qty = (Integer) quantitySpinner.getValue();
            int stock = selectedProduct.getInt("stock");
            if (qty > stock) {
                JOptionPane.showMessageDialog(this, "Not enough stock!");
                return;
            }

            // Update stock
            selectedProduct.put("stock", stock - qty);
            stockLabel.setText("Stock: " + selectedProduct.getInt("stock"));

            // Add to cart
            String productName = selectedProduct.getString("name");
            int productPrice = selectedProduct.getInt("price");
            Cart.addItem(productName, productPrice, qty);

            JOptionPane.showMessageDialog(this, "Added " + qty + " × " + productName + " to cart!");
        });
    }
}
