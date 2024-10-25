package tr.com.huseyinaydin.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Aspect
@Component
@Slf4j
public class ExceptionAlertAdvice {

    @Pointcut("execution (* tr.com.huseyinaydin.*.*.*(..))")
    public void alertFor() {

    }

    @AfterThrowing(value = "alertFor()", throwing = "exception")
    public void captureErrorAndSetAlerts(JoinPoint joinPoint, Exception exception) {
        log.error("metot imzası {}", joinPoint.getSignature());
        log.error("istisna mesajı  {}", exception.getMessage());

        if (exception instanceof IllegalArgumentException)
            System.out.println("merhaba IllegalArgumentException kardeş hoşgeldin ");
        if (exception instanceof RuntimeException)
            System.out.println("merhaba RuntimeException kardeş hoşgeldin ");
    }
}