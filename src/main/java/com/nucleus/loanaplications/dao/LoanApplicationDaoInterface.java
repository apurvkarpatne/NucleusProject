package com.nucleus.loanaplications.dao;

import com.nucleus.loanaplications.model.LoanApplications;

import java.util.List;

public interface LoanApplicationDaoInterface {
    boolean addApplication(LoanApplications loanApplication);
    List<LoanApplications> getLoanApplicationList();
}
