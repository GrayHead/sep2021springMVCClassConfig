import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.owu.entity.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure()
                .addAnnotatedClass(User.class)
                // from
                // hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        /*save*/
        User user = new User();
        user.setName("Oleg");

//        session.save(user);
//        session.persist(user);


        session.getTransaction().commit();


        List<User> select_u_from_user_u_ = session.createQuery("select u from User u ", User.class).getResultList();


        session.close();


    }
}
