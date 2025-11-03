package styles;

import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.json.*;

public class ProductsPage extends JPanel {

    private java.util.List<JSONObject> products;

    public ProductsPage(Consumer<String> onNavigate) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> onNavigate.accept("Home"));

        JLabel titleLabel = new JLabel("Available Products", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        loadProductsFromFile();

        JPanel productsPanel = new JPanel(new GridLayout(0, 3, 10, 10));

        for (JSONObject product : products) {
            JButton productButton = new JButton("<html><b>" + product.getString("name") + "</b><br>" +
                    "₱" + product.getDouble("price") + "</html>");

            productButton.addActionListener(e -> {
                ProductDetailsPage.setSelectedProduct(product);

                onNavigate.accept("ProductDetails");
            });

            productsPanel.add(productButton);
        }

        JScrollPane scrollPane = new JScrollPane(productsPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadProductsFromFile() {
        products = new ArrayList<>();

        try {
            String path = "src/data/products.json";
            String jsonText = Files.readString(Paths.get(path));
            JSONArray array = new JSONArray(jsonText);

            for (int i = 0; i < array.length(); i++) {
                products.add(array.getJSONObject(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load products.json");
        }
    }
}
