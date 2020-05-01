package UserDAO;

import ConfigDB.DBHelper;
import InterfaceDAO.UserDAO;
import Model.User;
;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    @Override
    public void deleteUser(User user) {
        try {
            user.setId(getIdByName(user.getName()));
            Connection connection = DBHelper.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            statement.setLong(1,user.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException s) {
            s.getStackTrace();
        }
    }
    @Override
    public List<User> selectAllUsers() {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            List<User> userList = new ArrayList<>();
            while (resultSet.next()){
                userList.add(new User
                        .Builder()
                        .withId(resultSet.getLong("id"))
                        .withName(resultSet.getString("nameuser"))
                        .withAge(resultSet.getLong("age"))
                        .build());
            }
            resultSet.close();
            return userList;
        } catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }
    @Override
    public void updateUser(User newUser) {
        Connection connection = DBHelper.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET nameuser=?,age=? where id=?");
            preparedStatement.setString(1,newUser.getName());
            preparedStatement.setLong(2,newUser.getAge());
            preparedStatement.setLong(3,newUser.getId());
            preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insertUser(User user) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (nameuser,age) VALUES (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getAge());
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public long getIdByName(String name) {
        Connection connection = DBHelper.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users where nameuser=?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            connection.close();
            return id;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }
    public void createTable() {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE users " +
                    "(id bigint auto_increment, nameuser varchar(256)" +
                    ",age bigint, primary key (id))");
            stmt.close();
            connection.close();
        } catch (Exception e){
            e.getStackTrace();
        }
    }
}
