package service.impl;

import exceptions.EntityNotFoundException;
import model.Product;
import repository.ProductRepository;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(Integer id) {
        Product product = null;
        try {
            product = productRepository.getById(id);
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public boolean delete(Product product) {
        if (!productRepository.delete(product)) {
            System.err.println("The product was not deleted");
            return false;
        }
        return true;
    }

}
