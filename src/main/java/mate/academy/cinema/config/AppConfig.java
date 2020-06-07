package mate.academy.cinema.config;

import java.util.Properties;
import javax.sql.DataSource;
import mate.academy.cinema.model.CinemaHall;
import mate.academy.cinema.model.Movie;
import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.model.Order;
import mate.academy.cinema.model.ShoppingCart;
import mate.academy.cinema.model.Ticket;
import mate.academy.cinema.model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {
        "mate.academy.cinema.service",
        "mate.academy.cinema.dao",
        "mate.academy.cinema.security"
})
public class AppConfig {
    private final Environment environment;

    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        var dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        var factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        var properties = new Properties();
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(Movie.class, CinemaHall.class, MovieSession.class,
                User.class, ShoppingCart.class, Ticket.class, Order.class);
        return factoryBean;
    }
}
