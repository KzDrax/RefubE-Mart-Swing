# RefurbE Mart Swing Application

## Overview
RefurbE Mart is a Java Swing-based application designed for a web-based commerce platform specializing in refurbished electronics. This project aims to provide a user-friendly interface for managing user accounts, viewing products, and handling shopping cart functionalities.

## Project Structure
```
RefurbE-Mart-Swing
├── src
│   ├── Main.java
│   ├── ui
│   │   ├── AccountPage.java
│   │   ├── CartPage.java
│   │   ├── HomePage.java
│   │   ├── ProductDetailsPage.java
│   │   └── ProductsPage.java
│   └── styles
│       └── Styles.java
├── README.md
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