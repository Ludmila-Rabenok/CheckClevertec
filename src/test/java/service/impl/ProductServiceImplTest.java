package service.impl;

import model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.ProductRepository;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private final ProductRepository productRepository = mock(ProductRepository.class);

    private ProductService productService;

    private static Product expectedProduct;
    private static List<Product> expectedProductList;

    @BeforeAll
    public static void init() {
        expectedProduct = new Product();
        expectedProduct.setId(1);
        expectedProduct.setAuction(true);
        expectedProduct.setPrice(25.2);
        expectedProduct.setName("product");
        expectedProductList = new ArrayList<>();
        expectedProductList.add(expectedProduct);
    }

    @BeforeEach
    public void prepareTestData() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void save() {
        when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);

        Product actualProduct = productService.save(expectedProduct);

        verify(productRepository, times(1)).save(expectedProduct);
        assertNotNull(actualProduct);
        assertSame(expectedProduct, actualProduct);
    }

    @Test
    void getAll() {
        when(productRepository.getAll()).thenReturn(expectedProductList);

        List<Product> productList = productService.getAll();

        assertEquals(1, productList.size());
        verify(productRepository, times(1)).getAll();
    }

    @Test
    void getById() {
        when(productRepository.getById(1)).thenReturn(expectedProduct);

        Product foundProduct = productService.getById(1);

        assertNotNull(foundProduct);
        assertSame(expectedProduct, foundProduct);
        verify(productRepository, timeout(1)).getById(1);
    }

    @Test
    void update() {
        when(productRepository.update(any(Product.class))).thenReturn(expectedProduct);

        expectedProduct.setName("Product2");
        expectedProduct.setPrice(55.5);
        expectedProduct.setAuction(false);
        Product actualProduct = productService.update(expectedProduct);

        verify(productRepository, times(1)).update(expectedProduct);
        assertNotNull(actualProduct);
        assertSame(expectedProduct, actualProduct);
    }

    @Test
    void delete() {
        when(productRepository.delete(any(Product.class))).thenReturn(true);

        boolean actual = productService.delete(expectedProduct);

        verify(productRepository, times(1)).delete(expectedProduct);
        assertTrue(actual);
    }

}
