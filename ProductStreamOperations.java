import java.util.*;
import java.util.stream.*;
import java.util.Comparator;

class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String toString() {
        return name + " | " + category + " | Price: " + price;
    }
}

public class ProductStreamOperations {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 80000, "Electronics"),
            new Product("Phone", 50000, "Electronics"),
            new Product("Shirt", 1500, "Clothing"),
            new Product("Jeans", 2500, "Clothing"),
            new Product("Fridge", 60000, "Electronics"),
            new Product("Blender", 3000, "Appliances"),
            new Product("Microwave", 7000, "Appliances")
        );

        // Grouping by Category
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));
        System.out.println("Grouped by Category:");
        grouped.forEach((category, list) -> {
            System.out.println(category + " -> " + list);
        });

        // Most expensive product in each category
        Map<String, Optional<Product>> mostExpensive = products.stream()
                .collect(Collectors.groupingBy(
                    p -> p.category,
                    Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));
        System.out.println("\nMost Expensive Product in Each Category:");
        mostExpensive.forEach((category, prod) -> 
            System.out.println(category + " -> " + prod.get()));

        // Average price of all products
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage Price of All Products: " + avgPrice);
    }
}
