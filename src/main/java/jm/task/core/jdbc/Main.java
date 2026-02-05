package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        // Создание таблицы User
        userService.createUsersTable();

        // Добавление 4х User в таблицу с данными. После каждого вывод на консоль (User с именем — name добавлен в базу данных)
        userService.saveUser("Ivan", "Ivanov", (byte) 30);
        userService.saveUser("Petr", "Petrov", (byte) 25);
        userService.saveUser("Mihail", "Mihailov", (byte) 20);
        userService.saveUser("Sidor", "Sidorov", (byte) 15);

//        // Получение всех User из БД и вывод на консоль (toString())
        userService.getAllUsers();

        // Очистка таблицы User
        userService.cleanUsersTable();

        // Удаление таблицы
        userService.dropUsersTable();
    }
}
