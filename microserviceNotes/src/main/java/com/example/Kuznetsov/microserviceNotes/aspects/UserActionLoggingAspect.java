package com.example.Kuznetsov.microserviceNotes.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class UserActionLoggingAspect {

    private static int actionCounter = 1;

    /**
     * Зарегистрировать и пронумеровать действия пользователя
     * @param joinPoint
     */
    @Before("@annotation(com.github.velocatcher.aspects.TrackUserAction)")
    public void logUserAction(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName(); // Название метода, который вызывается
        String className = joinPoint.getTarget().getClass().getSimpleName(); // Название класса, метод которого вызывается
        Object[] args = joinPoint.getArgs(); // Аргументы, с которыми вызывается метод, в виде массива

        System.out.printf("Действие пользователя %d: Метод %s в классе %s вызывается с аргументами: %s",
                actionCounter++, methodName, className, Arrays.toString(args));
        System.out.println();
    }

}
