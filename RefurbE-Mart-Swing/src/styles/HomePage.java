package styles;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import styles.Styles;

public class HomePage extends JPanel {
    public HomePage(Consumer<String> onNavigate) {
        setLayout(new GridLayout(3, 1, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(60, 200, 60, 200));

        JButton accountButton = new JButton("Account");
        Styles.applyButtonStyle(accountButton);
        accountButton.addActionListener(e -> onNavigate.accept("Account"));

        JButton productsButton = new JButton("Products");
        Styles.applyButtonStyle(productsButton);
        productsButton.addActionListener(e -> onNavigate.accept("Products"));

        JButton cartButton = new JButton("Cart");
        Styles.applyButtonStyle(cartButton);
        cartButton.addActionListener(e -> onNavigate.accept("Cart"));

        add(accountButton);
        add(productsButton);
        add(cartButton);
    }
}