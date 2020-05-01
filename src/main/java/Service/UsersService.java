package Service;

import DAOFactory.UserDaoFactory;
import InterfaceDAO.UserDAO;
import InterfaceService.InterfaceUsersService;
import UserDAO.*;
import Model.User;

import java.util.List;


public class UsersService implements InterfaceUsersService {

    private static UsersService instance = null;

    private UsersService() {
    }

    public static UsersService getInstance() {
        if (instance == null) {
            instance = new UsersService();
        }
        return instance;
    }

    public void updateUser(User newUser) {
        UserDAO userDAO = UserDaoFactory.getInstance().getUserDAO();
        userDAO.updateUser(newUser);
    }

    public List<User> selectAllUsers() {
        UserDAO userDAO = UserDaoFactory.getInstance().getUserDAO();
        return userDAO.selectAllUsers();
    }

    public void insertUser(User user) {
        UserDAO userDAO = UserDaoFactory.getInstance().getUserDAO();
        userDAO.insertUser(user);
    }

    public void deleteUser(User user) {
        UserDAO userDAO = UserDaoFactory.getInstance().getUserDAO();
        userDAO.deleteUser(user);
    }
    public long getIdByName(String name){
        UserDAO userDAO = UserDaoFactory.getInstance().getUserDAO();
        return userDAO.getIdByName(name);
    }

}
