package tr.com.huseyinaydin.contributor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Component
public class ApplicationContextContributor implements InfoContributor {

    @Autowired
    private ApplicationContext context;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("bean-definition-count", context.getBeanDefinitionCount());
        contextMap.put("bean-names", context.getBeanDefinitionNames());
        contextMap.put("application-startup-time", context.getStartupDate());
        builder.withDetail("context", contextMap);
    }
}