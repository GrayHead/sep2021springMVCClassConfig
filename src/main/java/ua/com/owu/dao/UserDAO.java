package ua.com.owu.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public List<User> findUsers() {
        List<User> resultList = entityManager.createQuery("select  u from User u", User.class).getResultList();
        return resultList;
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public void removeUser(int id) {
        User user = findById(id);
        entityManager.remove(user);
    }

    public void updateUser(User user) {
        entityManager.merge(user);

    }

}
