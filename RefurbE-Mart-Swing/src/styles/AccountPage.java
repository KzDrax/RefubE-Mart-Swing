package styles;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import styles.Styles;

public class AccountPage extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AccountPage(Consumer<String> onNavigate) {
        setLayout(new BorderLayout());

        // Back button
        JButton backButton = new JButton("Back");
        Styles.applyButtonStyle(backButton);
        backButton.addActionListener(e -> onNavigate.accept("Home"));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(onNavigate), "Login");
        mainPanel.add(createRegisterPanel(onNavigate), "Register");

        add(mainPanel, BorderLayout.CENTER);
        cardLayout.show(mainPanel, "Login");
    }

    private JPanel createLoginPanel(Consumer<String> onNavigate) {
        JPanel loginPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        Styles.applyButtonStyle(loginButton);
        JButton switchToRegisterButton = new JButton("Register");
        Styles.applyButtonStyle(switchToRegisterButton);

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(switchToRegisterButton);

        loginButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Login successful!");
            onNavigate.accept("Home");
        });

        switchToRegisterButton.addActionListener(e -> cardLayout.show(mainPanel, "Register"));

        return loginPanel;
    }

    private JPanel createRegisterPanel(Consumer<String> onNavigate) {
        JPanel registerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");
        Styles.applyButtonStyle(registerButton);
        JButton switchToLoginButton = new JButton("Login");
        Styles.applyButtonStyle(switchToLoginButton);

        registerPanel.add(new JLabel("Username:"));
        registerPanel.add(usernameField);
        registerPanel.add(new JLabel("Email:"));
        registerPanel.add(emailField);
        registerPanel.add(new JLabel("Password:"));
        registerPanel.add(passwordField);
        registerPanel.add(registerButton);
        registerPanel.add(switchToLoginButton);

        registerButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Registration successful!");
            cardLayout.show(mainPanel, "Login");
        });

        switchToLoginButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        return registerPanel;
    }
}