package DAOFactory;

import java.io.File;
import java.io.FileInputStream;

import InterfaceDAO.UserDAO;
import UserDAO.UserHibernateDAO;
import UserDAO.UserJdbcDAO;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    public static UserDaoFactory instance;

    private UserDaoFactory() {
    }

    public static UserDaoFactory getInstance() {
        if (instance == null) {
            instance = new UserDaoFactory();
        }
        return instance;
    }
    public UserDAO getUserDAO() {
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\GEORGY\\Downloads\\web_lesson_1.1.3-master\\src\\main\\resources\\config.properties"));
            Properties properties = new Properties();
            properties.load(file);
            if (properties.getProperty("daotype").equals("UserJdbcDAO")) {
                return new UserJdbcDAO();
            }
            if (properties.getProperty("daotype").equals("UserHibernateDAO")) {
                return new UserHibernateDAO();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
