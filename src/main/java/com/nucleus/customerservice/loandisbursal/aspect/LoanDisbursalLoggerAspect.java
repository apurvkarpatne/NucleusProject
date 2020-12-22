package com.nucleus.customerservice.loandisbursal.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoanDisbursalLoggerAspect {

    Logger LOGGER = LoggerFactory.getLogger(LoanDisbursalLoggerAspect.class);

    @After("execution (* com.nucleus.customerservice.loandisbursal.controller.LoanDisbursalController.getLoanDisbursals(..))")
    public void fetchbyLonaId(JoinPoint joinPoint) {
        LOGGER.info("Loan Disbursal Details fetched by LoanApplicationNumber {}", joinPoint);
    }

    @After("execution (* com.nucleus.customerservice.loandisbursal.controller.LoanDisbursalController.getLoanDisbursalsByCustomerId(..))")
    public void fetchbyCustomerId(JoinPoint joinPoint) {
        LOGGER.info("Loan Disbursal Details fetched by customerCode {}", joinPoint);
    }
}
