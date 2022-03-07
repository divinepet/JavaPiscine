package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class ProductsReposutoryJdbcImpl extends JdbcTemplate implements ProductsRepository {
    private JdbcTemplate jdbcTemplate;

    public ProductsReposutoryJdbcImpl(Connection connection) {
        this.jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
    }

    @Override
    public List<Product> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM product",
                (rs, rowNum) -> new Product(
                        rs.getInt("identifier"),
                        rs.getString("name"),
                        rs.getInt("price")
                ));
    }

    @Override
    public Optional<Product> findById(int id) {
        Product product = this.jdbcTemplate.queryForObject(format("SELECT * FROM product WHERE identifier = %d;", id),
                (rs, rowNum) -> new Product(
                        rs.getInt("identifier"),
                        rs.getString("name"),
                        rs.getInt("price")
                ));
        return (Optional.ofNullable(product));
    }

    @Override
    public void update(Product product) {
        this.jdbcTemplate.update(format("UPDATE product " +
                "SET name = '%s', price = %d " +
                "WHERE identifier = %d;", product.getName(), product.getPrice(), product.getId()));
    }

    @Override
    public void save(Product product) {
        this.jdbcTemplate.update(format("INSERT INTO product (identifier, name, price)" +
                        "VALUES (%d, '%s', %d);", product.getId(), product.getName(), product.getPrice()));
    }

    @Override
    public void delete(int id) {
        this.jdbcTemplate.update(format("DELETE FROM product WHERE identifier = %d;", id));
    }


}
