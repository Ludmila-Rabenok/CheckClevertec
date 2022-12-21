package repository.impl;

import dataSource.ProductDataSource;
import exceptions.EntityNotFoundException;
import model.Product;
import repository.ProductRepository;
import util.IdGenerator;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductDataSource productDataSource;

    public ProductRepositoryImpl(ProductDataSource productDataSource) {
        this.productDataSource = productDataSource;
    }

    @Override
    public Product save(Product product) {
        product.setId(IdGenerator.generateProductId());
        getAll().add(product);
        return product;
    }

    @Override
    public List<Product> getAll() {
        return productDataSource.getProducts();
    }

    @Override
    public Product getById(Integer id) throws EntityNotFoundException {
        Product product = getAll().stream()
                .filter(x -> id.equals(x.getId()))
                .findFirst()
                .orElse(null);
        if (product == null) {
            throw new EntityNotFoundException("No such product found");
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        Product productForUpdate = getById(product.getId());
        productForUpdate.setName(product.getName());
        productForUpdate.setPrice(product.getPrice());
        productForUpdate.setAuction(product.getAuction());
        return productForUpdate;
    }

    @Override
    public boolean delete(Product product) {
        return getAll().remove(product);
    }

}
