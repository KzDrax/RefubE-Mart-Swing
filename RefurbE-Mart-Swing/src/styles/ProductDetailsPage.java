package styles;

import javax.swing.*;

import org.json.JSONObject;

import java.awt.*;
import java.util.function.Consumer;
import styles.Styles;

// 🟩 Added import for CartItem class
import styles.CartItem;

public class ProductDetailsPage extends JPanel {
    private String productNameStr = "Product Name"; // 🟩 Placeholder product name
    private double productPrice = 0.0;              // 🟩 Placeholder product price
    private int availableStock = 10;                // 🟩 Placeholder stock count
    private JSpinner quantitySpinner;               // 🟩 Added: spinner for quantity
    private Consumer<String> onNavigate;
    private static JSONObject selectedProduct;

    public static void setSelectedProduct(JSONObject product) {
        selectedProduct = product;
    }

    public ProductDetailsPage(Consumer<String> onNavigate) {
        this.onNavigate = onNavigate; // 🟩 Added: store navigation reference
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        if (selectedProduct != null) {
        String name = selectedProduct.getString("name");
        double price = selectedProduct.getDouble("price");
        int stock = selectedProduct.getInt("stock");
        setProductDetails(name, price, stock);
        }

        // 🟩 Top Panel (Back button)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        Styles.applyButtonStyle(backButton);
        backButton.addActionListener(e -> onNavigate.accept("Products"));
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        // 🟩 Center Panel (Product Info)
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel productName = new JLabel(productNameStr);
        Styles.applyHeaderStyle(productName);

        JLabel productDescription = new JLabel(
            "<html>This is a refurbished gadget with excellent performance.<br>" +
            "All units are tested and certified for reuse.<br>" +
            "Price: ₱" + productPrice + "<br>" +
            "Available Stock: " + availableStock + "</html>"
        );
        productDescription.setFont(Styles.BODY_FONT);

        centerPanel.add(productName);
        centerPanel.add(productDescription);
        add(centerPanel, BorderLayout.CENTER);

        // 🟩 Bottom Panel (Quantity + Add to Cart)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        // 🟩 Added: Quantity Label and Spinner
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(Styles.BODY_FONT);
        bottomPanel.add(quantityLabel);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, availableStock, 1));
        quantitySpinner.setFont(Styles.BODY_FONT);
        bottomPanel.add(quantitySpinner);

        // 🟩 Added: Add to Cart button
        JButton addToCartButton = new JButton("Add to Cart");
        Styles.applyButtonStyle(addToCartButton);
        bottomPanel.add(addToCartButton);

        // 🟩 Added: Event to add item to cart
        addToCartButton.addActionListener(e -> {
            int selectedQuantity = (int) quantitySpinner.getValue();

            if (selectedQuantity > availableStock) {
                JOptionPane.showMessageDialog(this, "Not enough stock available!");
                return;
            }

            // 🟩 FIXED: Create a CartItem object instead of passing string
            Cart.addItem(new CartItem(productNameStr, productPrice, selectedQuantity));

            JOptionPane.showMessageDialog(this, 
                selectedQuantity + " x " + productNameStr + " added to cart!");
            
            availableStock -= selectedQuantity;
            onNavigate.accept("Cart"); // 🟩 Navigate to Cart page
        });

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // 🟩 Added: Method to set product details dynamically (called from ProductsPage)
    public void setProductDetails(String name, double price, int stock) {
        this.productNameStr = name;
        this.productPrice = price;
        this.availableStock = stock;
    }
}
        