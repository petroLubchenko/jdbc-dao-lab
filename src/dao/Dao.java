package dao;

import Entites.Base;

import java.util.ArrayList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<E extends Base> {
    public abstract void insert(E entity);
    public abstract E read(int id);
    public abstract void update(E entity);
    public abstract void delete(E entity);
    public abstract ArrayList<E> getall();
}
