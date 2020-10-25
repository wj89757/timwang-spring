package com.timwang.spring.other.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author wangjun
 * @date 2020-10-25
 */
@Component
@Aspect
public class TxAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.timwang.spring.other.transaction..*.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(JoinPoint joinPoint) {
        TransactionUtil.startTransaction();

        Object proceed = null;
        try {
            ProceedingJoinPoint method = (ProceedingJoinPoint) joinPoint;
            proceed = method.proceed();
        } catch (Throwable throwable) {
            TransactionUtil.rollback();
            return proceed;
        }
        TransactionUtil.commit();
        return proceed;
    }
}
