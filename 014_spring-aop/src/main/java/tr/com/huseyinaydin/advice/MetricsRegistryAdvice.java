package tr.com.huseyinaydin.advice;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MetricsRegistryAdvice {

    @Autowired
    private ObservationRegistry registry;

    //@After(value = "execution (* tr.com.huseyinaydin.controller.ProductController.*(..))")
    public void sendMetrics(JoinPoint joinPoint){
        //iş mantığı
        log.info("uygulama ölçüm bilgileri toplamasyon");
        Observation.createNotStarted(joinPoint.getSignature().getName(), registry)
                .observe(()->joinPoint.getArgs());
        log.info("uygulama ölçümleri yayınlanıyor");
    }
}