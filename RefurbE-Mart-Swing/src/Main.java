import javax.swing.*;
import java.awt.*;
//import ui.*;
import styles.*;

public class Main {
    private JFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private CartPage cartPage;

    // Card names
    private static final String HOME = "Home";
    private static final String ACCOUNT = "Account";
    private static final String PRODUCTS = "Products";
    private static final String CART = "Cart";
    private static final String PRODUCT_DETAILS = "ProductDetails";

    public Main() {
        mainFrame = new JFrame("RefurbE Mart");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 700);
        mainFrame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create pages (only ONCE)
        HomePage homePage = new HomePage(this::showPage);
        AccountPage accountPage = new AccountPage(this::showPage);
        ProductsPage productsPage = new ProductsPage(this::showPage);
        cartPage = new CartPage(this::showPage); // single instance
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(this::showPage);

        // Add pages (after refactoring them to JPanel)
        cardPanel.add(homePage, HOME);
        cardPanel.add(accountPage, ACCOUNT);
        cardPanel.add(productsPage, PRODUCTS);
        cardPanel.add(cartPage, CART); // ✅ use the same instance
        cardPanel.add(productDetailsPage, PRODUCT_DETAILS);

        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);

        showPage(HOME);
    }

    // Navigation method
    public void showPage(String pageName) {
        if (pageName.equals(CART)) {
            cartPage.updateCartDisplay();
        }
        cardLayout.show(cardPanel, pageName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}