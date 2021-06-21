import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.User;
import repository.UsersRepository;

import java.io.InputStream;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        InputStream input = Main.class.getClassLoader().getResourceAsStream("db.properties");
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        Object user = new User(1, "hui v palto");
        usersRepository.save(user);
//        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
//        System.out.println(usersRepository.findAll());
    }
}
