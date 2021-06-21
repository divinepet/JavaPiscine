package repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    DataSource dataSource;
    UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void save(Object entity) throws SQLException {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<String> findByEmail(String email) {
        return Optional.empty();
    }
}
