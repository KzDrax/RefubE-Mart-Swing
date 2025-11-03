package styles;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import org.json.JSONObject;
import styles.Styles;
import styles.CartItem;

public class ProductDetailsPage extends JPanel {

    private static JSONObject selectedProduct;
    private Consumer<String> onNavigate;
    private JLabel productNameLabel, productDescriptionLabel;
    private JSpinner quantitySpinner;
    private int availableStock;

    public static void setSelectedProduct(JSONObject product) {
        selectedProduct = product;
    }

    public ProductDetailsPage(Consumer<String> onNavigate) {
        this.onNavigate = onNavigate;
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        Styles.applyButtonStyle(backButton);
        backButton.addActionListener(e -> onNavigate.accept("Products"));
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        productNameLabel = new JLabel("Loading...");
        Styles.applyHeaderStyle(productNameLabel);

        productDescriptionLabel = new JLabel("Loading product details...");
        productDescriptionLabel.setFont(Styles.BODY_FONT);

        centerPanel.add(productNameLabel);
        centerPanel.add(productDescriptionLabel);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(Styles.BODY_FONT);
        bottomPanel.add(quantityLabel);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1, 1));
        quantitySpinner.setFont(Styles.BODY_FONT);
        bottomPanel.add(quantitySpinner);

        JButton addToCartButton = new JButton("Add to Cart");
        Styles.applyButtonStyle(addToCartButton);
        bottomPanel.add(addToCartButton);
        add(bottomPanel, BorderLayout.SOUTH);

        if (selectedProduct != null) {
            loadProductDetails();
        }

        addToCartButton.addActionListener(e -> {
            int selectedQuantity = (int) quantitySpinner.getValue();

            if (selectedQuantity > availableStock) {
                JOptionPane.showMessageDialog(this, "Not enough stock available!");
                return;
            }

            String name = selectedProduct.getString("name");
            double price = selectedProduct.getDouble("price");

            Cart.addItem(new CartItem(name, price, selectedQuantity));

            availableStock -= selectedQuantity;
            updateProductDescription();

            JOptionPane.showMessageDialog(this,
                selectedQuantity + " x " + name + " added to cart!");

            onNavigate.accept("Cart");
        });
    }

    private void loadProductDetails() {
        String name = selectedProduct.getString("name");
        double price = selectedProduct.getDouble("price");
        String description = selectedProduct.getString("description");
        availableStock = selectedProduct.getInt("stock");

        productNameLabel.setText(name);
        updateProductDescription();

        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, availableStock, 1);
        quantitySpinner.setModel(model);
    }

    private void updateProductDescription() {
        String description = selectedProduct.getString("description");
        double price = selectedProduct.getDouble("price");

        productDescriptionLabel.setText("<html>" +
            description + "<br>" +
            "Price: ₱" + price + "<br>" +
            "Available Stock: " + availableStock +
            "</html>");
    }
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible && selectedProduct != null) {
            loadProductDetails();
        }
    }
}
