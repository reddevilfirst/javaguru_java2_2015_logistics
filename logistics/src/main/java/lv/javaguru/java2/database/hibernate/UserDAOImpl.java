package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component("HibernateUserDAO")
@Transactional
public class UserDAOImpl extends DAOImpl<User> implements UserDAO {

    @Override
    public User getByLogin(String login) throws DBException {
        return (User) getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login)).uniqueResult();
    }

    @Override
    public User getById(Long id) throws DBException {
        return (User) getCurrentSession().get(User.class, id);

    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);
    }

    @Override
    public List<User> getAll() throws DBException {
        return getCurrentSession().createCriteria(User.class).list();
    }
}
