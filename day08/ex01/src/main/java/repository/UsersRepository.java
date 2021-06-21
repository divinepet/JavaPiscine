package repository;

import java.sql.SQLException;
import java.util.Optional;

public interface UsersRepository extends CrudRepository {
    Optional<String> findByEmail(String email);
}
