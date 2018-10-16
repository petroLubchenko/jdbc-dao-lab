package dao;

import Entites.User;

public abstract class UserDao implements Dao<User> {
    public abstract  void closeConnection();
}
