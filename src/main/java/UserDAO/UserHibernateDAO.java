package UserDAO;

import ConfigDB.DBHelper;
import InterfaceDAO.UserDAO;
import Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;


public class UserHibernateDAO implements UserDAO {

    private static SessionFactory sessionFactory = null;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = DBHelper.getInstance().getConfiguration();
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            return sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
    public static Session getSession(){
        return getSessionFactory().openSession();
    }

    @Override
    public void deleteUser(User user) {
        user.setId(getIdByName(user.getName()));
        Session session = UserHibernateDAO.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> selectAllUsers() {
        Session session = UserHibernateDAO.getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public void updateUser(User newUser) {
        Session session = UserHibernateDAO.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User set name=:name,age=:age where id=:id");
        query.setParameter("name",newUser.getName());
        query.setParameter("age",newUser.getAge());
        query.setParameter("id",newUser.getId());
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void insertUser(User user) {
        Session session = UserHibernateDAO.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public long getIdByName(String name) {
        Session session = UserHibernateDAO.getSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User where name=:name");
        query.setParameter("name",name);
        Long id = query.uniqueResult().getId();
        transaction.commit();
        session.close();
        return id;
    }
}
