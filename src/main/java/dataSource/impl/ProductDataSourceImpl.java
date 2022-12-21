package dataSource.impl;

import dataSource.ProductDataSource;
import model.Product;
import util.IdGenerator;

import java.util.ArrayList;

public class ProductDataSourceImpl implements ProductDataSource {

    private static ArrayList<Product> products = new ArrayList<>();

    static {
        Product product1 = new Product("Ball      ", 54.2, true);
        Product product2 = new Product("Table     ", 1054.5, true);
        Product product3 = new Product("Sofa      ", 2058.3, true);
        Product product4 = new Product("Computer  ", 2540.2, true);
        Product product5 = new Product("Basket    ", 50.0, true);
        Product product6 = new Product("Television", 1500.9, true);
        Product product7 = new Product("Painting  ", 10.2, false);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        setId();
    }

    public static void setId() {
        for (Product product : products) {
            product.setId(IdGenerator.generateProductId());
        }
    }

    @Override
    public ArrayList<Product> getProducts() {
        return products;
    }

}
