package tr.com.huseyinaydin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.entity.Customer;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public class BatchStepEventListener implements SkipListener<Customer, Number> {
    Logger logger = LoggerFactory.getLogger(BatchStepEventListener.class);

    @Override // öğe okuyucu
    public void onSkipInRead(Throwable throwable) {
        logger.info("Okuma esnasında hata {} ", throwable.getMessage());
    }

    @Override // öğe yazıcı
    public void onSkipInWrite(Number item, Throwable throwable) {
        logger.info("Yazma esnasında hata {} , {}", throwable.getMessage(), item);
    }

    /*
    @SneakyThrows, checked exception'ları yakalamak veya metot imzasında belirtmek zorunda kalmadan
    kullanmamı sağlıyor. Yani, normalde try-catch bloğuna ihtiyaç duyduğum yerlerde bu anotasyonla
    istisna yönetimini basitleştiriyorum. Kodun okunabilirliği açısından avantaj sağlasa da, arka planda
    neyin yakalandığını görmek zor olduğu için dikkatli kullanmam gerekiyor. Özellikle küçük projelerde
    pratik olsa da, büyük projelerde istisna yönetimini gözden kaçırmak riskli olabilir.
     */
    @SneakyThrows
    @Override // item processor
    public void onSkipInProcess(Customer customer, Throwable throwable) {
        logger.info("Öğe: {} hata nedeni ile atlandı. {}", new ObjectMapper().writeValueAsString(customer),
                throwable.getMessage());
    }
}