import java.util.HashMap;
import java.util.Map;

class Product {
    private String name;
    private double price;
    private int stockQuantity;

    public Product(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}

class ShoppingCart {
    private Map<Product, Integer> items;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        if (items.containsKey(product)) {
            items.put(product, items.get(product) + quantity);
        } else {
            items.put(product, quantity);
        }
    }

    public void removeItem(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            if (quantity >= currentQuantity) {
                items.remove(product);
            } else {
                items.put(product, currentQuantity - quantity);
            }
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    public void displayCart() {
        System.out.println("Shopping Cart:");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getName() + " x " + quantity + " - $" + product.getPrice() * quantity);
        }
        System.out.println("Total: $" + calculateTotal());
    }
}

public class StockAndBillingSystem {
    public static void main(String[] args) {
        Product product1 = new Product("Product A", 10.0, 100);
        Product product2 = new Product("Product B", 20.0, 50);
        
        ShoppingCart cart = new ShoppingCart();
        
        cart.addItem(product1, 3);
        cart.addItem(product2, 2);
        
        System.out.println("Before purchase:");
        cart.displayCart();
        
        // Simulate a purchase
        double purchaseAmount = cart.calculateTotal();
        
        // Update stock quantities
        product1.setStockQuantity(product1.getStockQuantity() - 3);
        product2.setStockQuantity(product2.getStockQuantity() - 2);
        
        System.out.println("\nAfter purchase:");
        cart.displayCart();
        
        System.out.println("\nStock quantities:");
        System.out.println(product1.getName() + ": " + product1.getStockQuantity());
        System.out.println(product2.getName() + ": " + product2.getStockQuantity());
    }
}
