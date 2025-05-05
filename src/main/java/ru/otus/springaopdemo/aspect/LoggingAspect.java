package ru.otus.springaopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* ru.otus.springaopdemo.service.*.*(..))")
    public void logBeforeServiceMethod(JoinPoint joinPoint) {
        System.out.println("[AOP-LOG] Вызов метода сервиса: " +
                joinPoint.getSignature().getName() +
                " с аргументами: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "execution(* ru.otus.springaopdemo.service.*.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AOP-LOG] Метод " + joinPoint.getSignature().getName() +
                " завершился. Результат: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* ru.otus.springaopdemo.service.*.*(..))",
            throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("[AOP-LOG] Ошибка в методе " +
                joinPoint.getSignature().getName() + ": " + ex.getMessage());
    }

    @Around("execution(* ru.otus.springaopdemo.controller.*.*(..))")
    public Object logAroundControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        System.out.println("[AOP-LOG] Начало работы " + methodName +
                " с аргументами: " + Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            System.out.println("[AOP-LOG] Метод " + methodName +
                    " выполнен за " + elapsedTime + " мс. Результат: " + result);
            return result;
        } catch (Exception e) {
            System.out.println("[AOP-LOG] Ошибка в " + methodName + ": " + e.getMessage());
            throw e;
        }
    }
}