package tr.com.huseyinaydin;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@SpringBootApplication
@RestController
public class SpringBulkProcessingApplication {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @PostMapping("/importCustomers-info")
    public ResponseEntity<?> importCustomerInfo2DB() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Toplu işlem bitti.");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBulkProcessingApplication.class, args);
    }
}