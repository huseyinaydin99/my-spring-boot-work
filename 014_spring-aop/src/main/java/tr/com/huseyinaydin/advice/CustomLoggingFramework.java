package tr.com.huseyinaydin.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Aspect
@Component
@Slf4j
public class CustomLoggingFramework {

    //return etmeden öncesi ve sonrası
    //@Around("execution (* tr.com.huseyinaydin.*.*.*(..))")
    //@Around("@annotation(tr.com.huseyinaydin.annotation.LogRequestAndResponse)")
    public Object captureRequestAndResponse(ProceedingJoinPoint pjp) throws Throwable {
        //öncesinde çalıştırılacaklar
        System.out.println("=====================ÖNCESİ BAŞLA==========================================");
        log.info("çalıştırma başladı {} ", pjp.getSignature());
        log.info("metot parametreleri {}", new ObjectMapper().writeValueAsString(pjp.getArgs()));
        System.out.println("=====================ÇALIŞMA ANI============================================");
        Object obj = pjp.proceed();

        //sonrasında çalıştırılacaklar
        System.out.println("=====================SONRASI BAŞLA===========================================");
        log.info("çalışma bitti {} ", pjp.getSignature());
        log.info("metodun geriye dönderdiği {}", new ObjectMapper().writeValueAsString(obj));
        System.out.println("=====================RETURN ANI=============================================");
        return obj;
    }
}