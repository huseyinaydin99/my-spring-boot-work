package tr.com.huseyinaydin.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.scheduling.annotation.Async;
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
public class LogExecutionTracker {

    @Around("@annotation(tr.com.huseyinaydin.annotation.TrackExecutionTime)")
    public Object logExecutionDuration(ProceedingJoinPoint pjp) throws Throwable {
        //metodun çalışma süresi hesaplama
        //öncesi
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed(); //metodun çalışma anı
        //sonrası
        long endTime = System.currentTimeMillis();
        log.info(" metot imzası: {} çalışma süresi {} milisaniyede tamamlandı ", pjp.getSignature(), (endTime - startTime));
        return obj;
    }
}