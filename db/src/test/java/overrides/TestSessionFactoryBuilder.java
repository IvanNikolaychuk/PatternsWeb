package overrides;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestSessionFactoryBuilder {
    private static SessionFactory sessionFactory;


    @PostConstruct
    public void setUp() {
        sessionFactory = new Configuration().configure("h2-hibernate-cfg.xml").buildSessionFactory();
    }

    public SessionFactory newInstance() {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("Session factory should have been initialized already, but it wasn't");
        }

        return sessionFactory;
    }
}
