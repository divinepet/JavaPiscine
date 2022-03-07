package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private Connection connection;
    private ProductsRepository productsRepository;
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS;

    public ProductsRepositoryJdbcImplTest() {}

    {
        Product product0 = new Product(0, "warrior", 700);
        Product product1 = new Product(1, "trickster", 800);
        Product product2 = new Product(2, "mage", 1000);
        Product product3 = new Product(3, "knight", 2000);
        Product product4 = new Product(4, "rogue", 1500);
        EXPECTED_FIND_ALL_PRODUCTS = new LinkedList<>();
        EXPECTED_FIND_ALL_PRODUCTS.add(product0);
        EXPECTED_FIND_ALL_PRODUCTS.add(product1);
        EXPECTED_FIND_ALL_PRODUCTS.add(product2);
        EXPECTED_FIND_ALL_PRODUCTS.add(product3);
        EXPECTED_FIND_ALL_PRODUCTS.add(product4);
    }


    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1, "trickster", 800);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2, "mage", 1000);
    final Product EXPECTED_SAVED_PRODUCT = new Product(5, "neckromant", 25);

    @BeforeEach
    public void setConnection() throws SQLException {
        this.connection = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build().getConnection();
        this.productsRepository = new ProductsReposutoryJdbcImpl(connection);
    }

    @Test
    public void testFindById() {
        Assertions.assertEquals(productsRepository.findById(1).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    public void testFindAll() {
        Assertions.assertIterableEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    public void testUpdate() {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(EXPECTED_UPDATED_PRODUCT.getId()).get());
    }

    @Test
    public void testSave() {
        EXPECTED_SAVED_PRODUCT.setId(6);
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, productsRepository.findById(6).get());
    }

    @Test
    public void testDelete() {
        productsRepository.delete(2);
        EmptyResultDataAccessException thrown = Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            productsRepository.findById(2);
        });
        System.out.println(thrown.getMessage());
        Assertions.assertTrue(thrown.getMessage().contains("Incorrect result size"));
    }
}
