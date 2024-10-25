package tr.com.huseyinaydin.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LoggingAdvice {

    // Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut("execution(* tr.com.huseyinaydin.*.*.*(..))")
    //@Pointcut("within(tr.com.huseyinaydin..*)")
    //@Pointcut("target(tr.com.huseyinaydin.service.ProductService)")
    //@Pointcut("execution(* tr.com.huseyinaydin.service.ProductService.get*(int))")
    //@Pointcut("execution(* tr.com.huseyinaydin.controller.ProductController.*(..)) || " +
    //"execution(* tr.com.huseyinaydin.service.ProductService.*(..))")
    private void logPointcut() {
    }

    //@Before("logPointcut()")
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("class ismi {}, metot ismi {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("metot parametreleri {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

    //@AfterReturning(value = "execution (* tr.com.huseyinaydin.controller.ProductController.*(..))", returning = "object")
    //public void logResponse(JoinPoint joinPoint,Object object) throws JsonProcessingException {
    //log.info("LoggingAdvice::logResponse class ismi {}, metot ismi {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
    //log.info("LoggingAdvice::logResponse metodun return ettiği değer veya referans {} ", new ObjectMapper().writeValueAsString(object));
    //}

    //@After(value = "execution (* tr.com.huseyinaydin.controller.ProductController.*(..))")
    public void logResponse(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("LoggingAdvice::logResponse class ismi {}, metot ismi {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("LoggingAdvice::logResponse metodun parametreleri {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }
}