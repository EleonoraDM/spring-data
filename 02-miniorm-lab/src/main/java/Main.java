

import entity.User;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/fsd";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Connection connection = getConnection();
        EntityManager<User> entityManager = new EntityManager<>(connection);

        System.out.println("Connected to database.");
        User user = new User();
        user.setId(6);
        user.setUsername("999999");
        user.setPassword("!!!!!!!!!!");
        user.setAge(42);
        user.setRegistrationDate(LocalDate.of(2000,11,1));

        entityManager.persist(user);


        System.out.println(entityManager.findFirst(User.class, " where username like '%8'"));
        System.out.println(entityManager.findById(User.class, 6));
        System.out.println(entityManager.delete(User.class, 9));

    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING, "root", "root");
    }
}