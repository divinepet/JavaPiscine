package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmbeddedDataSourceTest {
    DataSource dataSource;

    @BeforeEach()
    public void init() {
        EmbeddedDatabaseBuilder db = new EmbeddedDatabaseBuilder();
        dataSource = db
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("./schema.sql")
                .addScript("./data.sql")
                .build();
    }

    @Test
    public void getConnectionTest() throws SQLException {
        assertNotNull(dataSource.getConnection());
    }
}
