package com.ruoyi.framework.aspectj;

import com.ruoyi.framework.aspectj.lang.annotation.Servicelock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  Aspect把当前类作为一个切面类
 */

@Component
@Aspect
@Order(1)
//order越小越是最先执行，但更重要的是最先执行的最后结束。order默认值是2147483647
public class LockAspect {

    private ReentrantLock lock = new ReentrantLock();


    @Pointcut("@annotation(com.ruoyi.framework.aspectj.lang.annotation.Servicelock)")
    public void lockAspect(){

    }

    @Around("lockAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        lock.lock();
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
        return obj;
    }
}
