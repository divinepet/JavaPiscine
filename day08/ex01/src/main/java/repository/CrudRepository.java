package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    void save(T entity) throws SQLException;
    void update(T entity);
    void delete(Long id);
}
