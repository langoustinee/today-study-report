package com.kakao.lango.moviereview.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Log4j2
@Component // 빈을 자동으로 등록해준다.
@Aspect
public class EmployeeServiceAspect {
    @Before(value = "execution(* com.kakao.lango.moviereview.service.EmployeeService.*(..)) and args(empId, fname, sname)")
    void beforeAdvice(JoinPoint joinPoint, String empId, String fname, String sname) {
        log.info("Before method " + joinPoint.getSignature());

    }

    @After(value = "execution(* com.kakao.lango.moviereview.service.EmployeeService.*(..)) and args(empId, fname, sname)")
    void afterAdvice(JoinPoint joinPoint, String empId, String fname, String sname) {
        log.info("After method " + joinPoint.getSignature());
    }
}
