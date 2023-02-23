package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }

   public User getCarOwner(String carModel,int carSeries) {

      Session session = sessionFactory.getCurrentSession();
      return session.createQuery("from User user where user.car.model =: carModel and user.car.series =: carSeries", User.class).setParameter("carModel", carModel).setParameter("carSeries", carSeries).uniqueResult();
     // Car car = session.get(Car.class,model,series);



   }
}
