package dao;

import Entites.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class UserJDBCDao extends UserDao {
    private final String INSERT_NEW = "INSERT INTO users(id, firstname, secondname, age) VALUES (?, ?, ?, ?)";
    private final String GET_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String UPDATE = "UPDATE users SET firstname=?, secondname=?, age=? WHERE id=?";
    private final String DELETE = "DELETE FROM users WHERE id=?";
    private final String SELECT_ALL = "SELECT * FROM users";
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/labor?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "123qweasd";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserJDBCDao() throws ClassNotFoundException {
       // Class.forName("com.mysql.cj.jdbc.Driver");
        try{
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        if (connection != null)
        {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insert(User entity) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(INSERT_NEW);
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setString(2, entity.getFirstname());
                preparedStatement.setString(3, entity.getSecondname());
                preparedStatement.setInt(4, entity.getAge());
                preparedStatement.execute();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public User read(int id) {
        if (connection != null){
            try {
                preparedStatement = connection.prepareStatement(GET_BY_ID);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String firstname = resultSet.getString(2);
                    String secondname = resultSet.getString(3);
                    int age = resultSet.getInt(4);
                    return new User(id, firstname, secondname, age);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void update(User entity) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(UPDATE);
                preparedStatement.setString(1, entity.getFirstname());
                preparedStatement.setString(2, entity.getSecondname());
                preparedStatement.setInt(3, entity.getAge());
                preparedStatement.setInt(4, entity.getId());
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(User entity) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(DELETE);
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<User> getall() {
        ArrayList<User> res = new ArrayList<>();

        if (connection != null){
            Statement statement = null;
            try{
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL);
                while (resultSet.next()){
                    int userid = resultSet.getInt(1);
                    String firstname = resultSet.getString(2);
                    String secondname = resultSet.getString(3);
                    int age = resultSet.getInt(4);
                    res.add(new User(userid, firstname, secondname, age));
                }
                return res;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
