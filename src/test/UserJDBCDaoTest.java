package test;

import Entites.User;
import dao.UserDao;
import dao.UserJDBCDao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserJDBCDaoTest {
    UserDao userDao;
    User user1;
    User user2;
    User user4;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {
        userDao = new UserJDBCDao();
        user1 = new User(0, "Vasya", "Ivanov", 20);
        user2 = new User(1, "Jack", "Deere", 66);
        user4 = new User(0, "Vasilii", "Ivanov", 666);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        userDao.closeConnection();
        userDao = null;
        user1 = null;
        user2 = null;
    }

    @org.junit.jupiter.api.Test
    void insert() {
        userDao.insert(user1);
        userDao.insert(user2);
        User u3 = userDao.read(user1.getId());
        assertTrue(u3.equals(user1));
        u3 = userDao.read(user2.getId());
        assertTrue(u3.equals(user2));
        System.out.println("Insert succesful");
    }

    @org.junit.jupiter.api.Test
    void read() {
        User user3 = userDao.read(user1.getId());
        if(!user4.equals(user3))
            System.out.println("Method Read > Problems found");
        assertTrue(user4.equals(user3));
        user3 = userDao.read(user2.getId());
        assertTrue(user3.equals(user2));
        System.out.println("Read Succesful");
    }

    @org.junit.jupiter.api.Test
    void update() {
        userDao.update(user4);
        User u1 = userDao.read(user1.getId());
        assertTrue(u1.equals(user4));
        System.out.println("Update succesful");
    }

    @org.junit.jupiter.api.Test
    void delete() {
        userDao.delete(user1);
        userDao.delete(user2);
        assertTrue(userDao.getall().isEmpty());
        System.out.println("Delete succesful");
    }

    @org.junit.jupiter.api.Test
    void getall() {
        ArrayList<User> users = userDao.getall();
        if (!users.isEmpty()){
            assertTrue(users.get(0).equals(user1) || users.get(0).equals(user4));
            assertTrue(users.get(1).equals(user2));
        }

        System.out.println("Getall succesful");
    }
}