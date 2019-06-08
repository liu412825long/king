package cn.bj.king.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private static Logger logger= LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("execution(* cn.bj.king.service.impl.*.*(..))")
    public void webLog(){};

    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        System.out.println("前置通知");

    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("方法的返回值 : " + ret);
    }

    @AfterThrowing("webLog()")
    public void exception(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }

    @After("webLog()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint pjp) {
        System.out.println("方法环绕start.....");
        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        Class<?> c=signature.getMethod().getReturnType();
//        if(boolean.class.getName().equals(c.getName()) || Boolean.class.getName().equals(c.getName())){
//            return false;
//        }

        try {
            Object o =  pjp.proceed();
            System.out.println("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            System.out.println("出现异常。。。");
            logger.error(e.getMessage());
            return null;
        }
    }
}
