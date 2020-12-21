package com.nucleus.eligibiltyparameter.aspect;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DatabaseLogging {

    private static final Logger logger = Logger.getLogger(DatabaseLogging.class);

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.insertParameter(..))")
    public void insertParameterMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.getAll(..))")
    public void getAllParametersMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.insertParameterAndRequestApproval(..))")
    public void requestApprovalMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.getOneEligibilityParameter(..))")
    public void getOneParameterMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.deleteEligibilityParameter(..))")
    public void deleteParameterMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.editParameter(..))")
    public void editParameterMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.updateStatus(..))")
    public void updateStatusMethod() {
    }

    @Before("insertParameterMethod()")
    public void beforeInsertingParameter(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Inserting Eligibility Parameter(DATABASE): ");
        for (Object obj : joinPoint.getArgs()) {
            logger.info(obj);
        }
    }

    @AfterReturning(pointcut = "insertParameterMethod()", returning = "parameterCode")
    public void afterRegistering(JoinPoint joinPoint, Object parameterCode) {
        logger.info("(@AFTER RETURNING)Added Eligibility Parameter with Parameter Code(DATABASE): " + parameterCode);
    }

    @Before("getAllParametersMethod()")
    public void beforeGettingParameters(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Getting All Eligibility Parameters(DATABASE): ");
        }


    @AfterReturning("getAllParametersMethod()")
    public void afterGettingParameters(JoinPoint joinPoint) {
        logger.info("(@AFTER RETURNING)Got All Eligibility Parameters(DATABASE): ");
    }

    @Before("requestApprovalMethod()")
    public void beforeRequestingApproval(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Inserting Eligibility Parameter(DATABASE): ");
        }


    @AfterReturning(pointcut = "requestApprovalMethod()", returning = "parameterCode")
    public void afterRequestingApproval(JoinPoint joinPoint, Object parameterCode) {
        logger.info("(@AFTER RETURNING)Inserted Eligibility Parameter and requested for approval with Parameter Code : (DATABASE): " + parameterCode);
    }

    @Before("getOneParameterMethod()")
    public void beforeGettingParameter(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Getting Eligibility Parameter(DATABASE): ");
    }


    @AfterReturning(pointcut = "getOneParameterMethod()", returning = "eligibilityParameter")
    public void afterGettingParameter(JoinPoint joinPoint, Object eligibilityParameter) {
        EligibilityParameter ep=(EligibilityParameter)eligibilityParameter;
        logger.info("(@AFTER RETURNING)Got Eligibility Parameter with Parameter Code : (DATABASE): " + ep.getParameterCode());
    }

    @Before("deleteParameterMethod()")
    public void beforeDeletingParameter(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Deleting Eligibility Parameter(DATABASE): ");
    }


    @AfterReturning(pointcut = "deleteParameterMethod()", returning = "parameterCode")
    public void afterDeletingParameter(JoinPoint joinPoint, Object parameterCode) {
        logger.info("(@AFTER RETURNING)Deleted Eligibility Parameter with Parameter Code : (DATABASE): " + parameterCode);
    }

    @Before("editParameterMethod()")
    public void beforeEditingParameter(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Editing Eligibility Parameter(DATABASE): ");
    }


    @AfterReturning(pointcut = "editParameterMethod()")
    public void afterEditingParameter(JoinPoint joinPoint) {
        logger.info("(@AFTER RETURNING)Edited Eligibility Parameter Successfully (DATABASE): ");
    }

    @Before("updateStatusMethod()")
    public void beforeUpdatingStatus(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Updating Status(DATABASE): ");
    }


    @AfterReturning(pointcut = "updateStatusMethod()")
    public void afterUpdatingStatus(JoinPoint joinPoint) {
        logger.info("(@AFTER RETURNING)Updated Status Successfully (DATABASE): ");
    }
}

