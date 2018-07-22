package com.mghostl.education.stepik.webservices.dbauth.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.logging.Logger;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public  UsersDataSet get(long id) {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String name) {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public long insertUser(String login, String password) {
        return (Long) session.save(new UsersDataSet(login, password));
    }
}
