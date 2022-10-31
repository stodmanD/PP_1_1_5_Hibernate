package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    private static final UserService user = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        util.getConnection();
        user.createUsersTable();
        user.saveUser("Иван", "Иванов", (byte) 23);
        user.saveUser("Никита", "Петров", (byte) 31);
        user.saveUser("Илья", "Житов", (byte) 53);
        user.saveUser("Рулон", "Обоев", (byte) 61);
        user.removeUserById(3);
//        for (User allUser : user.getAllUsers()) {
//            System.out.println(allUser);
//        }
   //     user.cleanUsersTable();
     //   user.dropUsersTable();


        // реализуйте алгоритм здесь
    }
}
