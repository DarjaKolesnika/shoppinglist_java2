package ui;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConsoleUI {
    private ProductService productService = new ProductService();

    public void execute() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Edit product");
                System.out.println("4. Delete product");
                System.out.println("5. Exit");
                int userInput = Integer.parseInt(reader.readLine());
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        findProduct();
                        break;
                    case 3:
                        editProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter product name: ");
        String name = reader.readLine();
        System.out.println("Enter product description: ");
        String description = reader.readLine();
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(reader.readLine());
        System.out.println("Enter discount price: ");
        BigDecimal discount = new BigDecimal(reader.readLine());

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setDiscount(discount.setScale(1, RoundingMode.CEILING));
        product.setPrice(price.setScale(2, RoundingMode.CEILING));

        System.out.println("Choose product category. Press 1 for FRUITS, 2 for VEGETABLES, " +
                "3 for DAIRY, 4 for ALCOHOL, 5 for MEAT.");
        int userInputCategory = Integer.parseInt(reader.readLine());
        switch (userInputCategory) {
            case 1:
                product.setCategory(Product.Category.FRUITS);
                break;
            case 2:
                product.setCategory(Product.Category.VEGETABLES);
                break;
            case 3:
                product.setCategory(Product.Category.DAIRY);
                break;
            case 4:
                product.setCategory(Product.Category.ALCOHOL);
                break;
            case 5:
                product.setCategory(Product.Category.MEAT);
                break;
            default:
                System.out.println("Please enter the correct option");
        }

        productService.createProduct(product);
        System.out.println("Your product was successfully added to database, id: " + product.getId());
    }

    private void findProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter product id: ");
        Long id = Long.parseLong(reader.readLine());
        Product product = productService.findProductById(id);
        product.printInfoAboutProduct();
    }

    private void editProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter product id: ");
        Long id = Long.parseLong(reader.readLine());
        Product product = productService.findProductById(id);
        System.out.println("Please select an operation. Press 1 for editing product name, 2 for editing product price, " +
                "3 for editing or adding discount, 4 for editing description, 5 for exit");
        int userInput = Integer.parseInt(reader.readLine());
        switch (userInput) {
            case 1:
                System.out.println("Enter new product name: ");
                String name = reader.readLine();
                product.setName(name);
                product.printInfoAboutProduct();
                break;
            case 2:
                System.out.println("Enter new product price: ");
                BigDecimal newPrice = new BigDecimal(reader.readLine());
                product.setPrice(newPrice.setScale(2, RoundingMode.CEILING));
                product.printInfoAboutProduct();
                break;
            case 3:
                System.out.println("Enter new product discount: ");
                BigDecimal newDiscount = new BigDecimal(reader.readLine());
                product.setDiscount(newDiscount.setScale(1, RoundingMode.CEILING));
                product.printInfoAboutProduct();
                break;
            case 4:
                System.out.println("Enter new product description: ");
                String newDescription = reader.readLine();
                product.setDescription(newDescription);
                product.printInfoAboutProduct();
                break;
            case 5:
                return;
        }
        productService.editProduct(product);
    }

    private void deleteProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter product id: ");
        Long id = Long.parseLong(reader.readLine());
        productService.deleteProduct(id);
    }
}

