package com.nucleus.customerservice.loandisbursal.database;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanDisbursalDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public LoanApplications getLoanDetails(int loanApplicationNumber){
        Session session = sessionFactory.openSession();
        LoanApplications loanApplication = session.get(LoanApplications.class, loanApplicationNumber);
        session.close();
        return loanApplication;
    }

    public List<LoanApplications> getCustomerLoanDetails(String customerCode){
        Session session = sessionFactory.openSession();
        List<LoanApplications> loanApplications=null;
        Customer customer = session.get(Customer.class, customerCode);
        if(customer != null) {
            loanApplications=customer.getLoanApplications();
        }
        session.close();
        return loanApplications;
    }
}
