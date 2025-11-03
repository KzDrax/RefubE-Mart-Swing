# RefurbE Mart Swing Application

## Overview
RefurbE Mart is a Java Swing-based application designed for a web-based commerce platform specializing in refurbished electronics. This project aims to provide a user-friendly interface for managing user accounts, viewing products, and handling shopping cart functionalities.

## Project Structure
```
RefurbE-Mart-Swing/
│
├── 📁 bin/                           # Compiled .class files (auto-generated after javac)
│
├── 📁 lib/
│   └── json-20240303.jar             # JSON library dependency
│
├── 📁 src/
│   ├── 📁 data/
│   │   └── products.json             # JSON data file containing product info
│   │
│   ├── 📁 styles/                    # All Java Swing UI files
│   │   ├── Styles.java               # Reusable styles (fonts, colors, button styles)
│   │   ├── HomePage.java             # Homepage panel
│   │   ├── ProductsPage.java         # Product listing page
│   │   ├── ProductDetailsPage.java   # Displays product info dynamically from JSON
│   │   ├── CartPage.java             # Shopping cart page
│   │   ├── Cart.java                 # Handles cart logic (list, add, remove, etc.)
│   │   └── CartItem.java             # Model for each cart item (name, price, qty)
│   │
│   └── Main.java                     # Entry point for the application
│
└── README.md                         # Optional documentation or setup instructions
```

## File Descriptions

- **src/Main.java**: Entry point of the application. Initializes the Swing application and sets up the main frame.

- **src/ui/AccountPage.java**: Represents the account page UI. Contains methods to create the login and registration forms, handle user input, and display relevant messages.

- **src/ui/CartPage.java**: Represents the cart page UI. Displays the items in the user's cart and provides options to proceed to checkout or continue shopping.

- **src/ui/HomePage.java**: Represents the home page UI. Displays the main features of the application, including navigation to other pages and featured products.

- **src/ui/ProductDetailsPage.java**: Represents the product details page UI. Shows detailed information about a selected product, including images, descriptions, and purchase options.

- **src/ui/ProductsPage.java**: Represents the products page UI. Displays a list of available products and allows users to filter or search for specific items.

- **src/styles/Styles.java**: Contains constants and methods for styling the Swing components, including colors, fonts, and layout settings.

## Setup Instructions
1. Clone the repository or download the project files.
2. Open the project in your preferred Java IDE.
3. Ensure you have Java Development Kit (JDK) installed.
4. Compile the project and run `Main.java` to start the application.

## Dependencies
- Java JDK 8 or higher
- Swing library (included in the JDK)

## Usage
- Navigate through the application using the provided UI components.
- Users can create an account, log in, view products, and manage their shopping cart.

## License
This project is licensed under the MIT License.
