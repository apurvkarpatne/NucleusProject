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
public class ServiceLogging {

    private static final Logger logger = Logger.getLogger(ServiceLogging.class);

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.insertParameter(..))")
    public void insertParameterMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.getAll(..))")
    public void getAllParametersMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.insertParameterAndRequestApproval(..))")
    public void requestApprovalMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.getOneEligibilityParameter(..))")
    public void getOneParameterMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.deleteEligibilityParameter(..))")
    public void deleteParameterMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.editParameter(..))")
    public void editParameterMethod() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.updateStatus(..))")
    public void updateStatusMethod() {
    }

    @Before("insertParameterMethod()")
    public void beforeInsertingParameter(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Inserting Eligibility Parameter(SERVICE): ");
        for (Object obj : joinPoint.getArgs()) {
            logger.info(obj);
        }
    }

    @AfterReturning(pointcut = "insertParameterMethod()", returning = "parameterCode")
    public void afterRegistering(JoinPoint joinPoint, Object parameterCode) {
        logger.info("(@AFTER RETURNING)Added Eligibility Parameter with Parameter Code(SERVICE): " + parameterCode);
    }

    @Before("getAllParametersMethod()")
    public void beforeGettingParameters(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Getting All Eligibility Parameters(SERVICE): ");
    }


    @AfterReturning("getAllParametersMethod()")
    public void afterGettingParameters(JoinPoint joinPoint) {
        logger.info("(@AFTER RETURNING)Got All Eligibility Parameters(SERVICE): ");
    }

    @Before("requestApprovalMethod()")
    public void beforeRequestingApproval(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Inserting Eligibility Parameter(SERVICE): ");
    }


    @AfterReturning(pointcut = "requestApprovalMethod()", returning = "parameterCode")
    public void afterRequestingApproval(JoinPoint joinPoint, Object parameterCode) {
        logger.info("(@AFTER RETURNING)Inserted Eligibility Parameter and requested for approval with Parameter Code : (SERVICE): " + parameterCode);
    }

    @Before("getOneParameterMethod()")
    public void beforeGettingParameter(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Getting Eligibility Parameter(SERVICE): ");
    }


    @AfterReturning(pointcut = "getOneParameterMethod()", returning = "eligibilityParameter")
    public void afterGettingParameter(JoinPoint joinPoint, Object eligibilityParameter) {
        EligibilityParameter ep=(EligibilityParameter)eligibilityParameter;
        logger.info("(@AFTER RETURNING)Got Eligibility Parameter with Parameter Code : (SERVICE): " + ep.getParameterCode());
    }

    @Before("deleteParameterMethod()")
    public void beforeDeletingParameter(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Deleting Eligibility Parameter(SERVICE): ");
    }


    @AfterReturning(pointcut = "deleteParameterMethod()", returning = "parameterCode")
    public void afterDeletingParameter(JoinPoint joinPoint, Object parameterCode) {
        logger.info("(@AFTER RETURNING)Deleted Eligibility Parameter with Parameter Code : (SERVICE): " + parameterCode);
    }

    @Before("editParameterMethod()")
    public void beforeEditingParameter(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Editing Eligibility Parameter(SERVICE): ");
    }


    @AfterReturning(pointcut = "editParameterMethod()")
    public void afterEditingParameter(JoinPoint joinPoint) {
        logger.info("(@AFTER RETURNING)Edited Eligibility Parameter Successfully (SERVICE): ");
    }

    @Before("updateStatusMethod()")
    public void beforeUpdatingStatus(JoinPoint joinPoint) {
        logger.info("(@BEFORE)Updating Status(SERVICE): ");
    }


    @AfterReturning(pointcut = "updateStatusMethod()")
    public void afterUpdatingStatus(JoinPoint joinPoint) {
        logger.info("(@AFTER RETURNING)Updated Status Successfully (SERVICE): ");
    }
}

