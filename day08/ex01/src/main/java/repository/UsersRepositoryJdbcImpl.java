package repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    public DataSource dataSource;
    private final String tableName;

    public UsersRepositoryJdbcImpl(DataSource dataSource, String tableName) {
        this.dataSource = dataSource;
        this.tableName = tableName;
    }

    @Override
    public Optional<String> findByEmail(String email) {
        return Optional.empty();
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


    public void save(User entity) throws SQLException {
        String query = format("INSERT INTO %s (id, email) VALUES ('%d', %s);",
                tableName,
                entity.getId(),
                entity.getEmail());
        dataSource.getConnection().createStatement().executeUpdate(query);
    }

//    public void create() throws SQLException {
//        dataSource.getConnection().createStatement().execute("CREATE table " + tableName + "(id bigint, name varchar(20), email varchar(20)" + ");");
//        System.out.println();
//    }

//    public void save(User entity) throws SQLException {
//        String query = format("INSERT INTO %s (id, email) VALUES ('%d', %s);",
//                tableName,
//                entity.getId(),
//                entity.getEmail());
//        dataSource.getConnection().createStatement().executeUpdate(query);
//    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
