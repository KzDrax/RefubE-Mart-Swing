package styles;

import java.awt.Color;
import java.awt.Font;

public class Styles {
    public static final Color PRIMARY_COLOR = new Color(34, 34, 34);
    public static final Color SECONDARY_COLOR = new Color(255, 255, 255);
    public static final Color ACCENT_COLOR = new Color(0, 150, 136);
    
    public static final Font HEADER_FONT = new Font("Poppins", Font.BOLD, 24);
    public static final Font BODY_FONT = new Font("Poppins", Font.PLAIN, 16);
    public static final Font BUTTON_FONT = new Font("Poppins", Font.BOLD, 18);
    
    public static final int BUTTON_HEIGHT = 40;
    public static final int BUTTON_WIDTH = 120;

    public static void applyButtonStyle(javax.swing.JButton button) {
        button.setBackground(ACCENT_COLOR);
        button.setForeground(SECONDARY_COLOR);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    public static void applyHeaderStyle(javax.swing.JLabel label) {
        label.setFont(HEADER_FONT);
        label.setForeground(PRIMARY_COLOR);
    }

    public static void applyBodyStyle(javax.swing.JTextArea textArea) {
        textArea.setFont(BODY_FONT);
        textArea.setForeground(PRIMARY_COLOR);
        textArea.setBackground(SECONDARY_COLOR);
    }
}