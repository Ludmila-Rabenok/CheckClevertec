package repository;

import model.Product;

import java.util.List;


public interface ProductRepository {

    Product save(Product product);

    List<Product> getAll();

    Product getById(Integer id);

    Product update(Product product);

    boolean delete(Product product);

}
