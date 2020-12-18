package com.nucleus.loanaplications.dao;

import com.nucleus.loanaplications.model.LoanApplications;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanApplicationDAO implements LoanApplicationDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException E){
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    public boolean addApplication(LoanApplications loanApplication) {
        return false;
    }

    @Override
    public List<LoanApplications> getLoanApplicationList() {
        return null;
    }
}
