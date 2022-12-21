package service;

import model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> getAll();

    Product getById(Integer id);

    Product update(Product product);

    boolean delete(Product product);

}
