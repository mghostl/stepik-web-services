package com.mghostl.education.stepik.webservices.dbauth.services;

import com.mghostl.education.stepik.webservices.dbauth.dao.UsersDAO;
import com.mghostl.education.stepik.webservices.dbauth.dao.UsersDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DBServiceImpl implements DBService{
    private static final String HIBERNATE_SHOW_SQL = "true";
    private static final String HIBERNATE_HBM2DDL_AUTO = "create";
    private static final Logger LOGGER = Logger.getGlobal();

    private final SessionFactory sessionFactory;

    public DBServiceImpl() {
        Configuration configuration = getH2Configuration();
        this.sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);

        return configuration
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url", "jdbc:h2:./h2db")
                .setProperty("hibernate.connection.username", "test")
                .setProperty("hibernate.connection.password", "test")
                .setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL)
                .setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);

    }

    @Override
   public UsersDataSet getUser(Long userId) {
        Session session = sessionFactory.openSession();
        UsersDAO dao = new UsersDAO(session);
        UsersDataSet dataSet = dao.get(userId);
        session.close();
        return dataSet;
    }

    @Override
    public long addUser(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersDAO dao = new UsersDAO(session);
        long id = dao.insertUser(login, password);
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public void printConnectInfo() {
        SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
        Connection connection;
        try {
            connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            LOGGER.info("DB name : " + connection.getMetaData().getConnection());
            LOGGER.info("DB version: " + connection.getMetaData().getDatabaseProductName());
            LOGGER.info("Driver: " + connection.getMetaData().getDatabaseProductVersion());
            LOGGER.info("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
           LOGGER.info("ERROR: " + e.getMessage());
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
         builder.applySettings(configuration.getProperties());
         ServiceRegistry serviceRegistry = builder.build();
         return configuration.buildSessionFactory(serviceRegistry);
    }
}