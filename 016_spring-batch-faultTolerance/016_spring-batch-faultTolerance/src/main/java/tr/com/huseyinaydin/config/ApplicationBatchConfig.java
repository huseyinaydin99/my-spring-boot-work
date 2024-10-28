package tr.com.huseyinaydin.config;

import tr.com.huseyinaydin.entity.Customer;
import tr.com.huseyinaydin.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Configuration
@EnableBatchProcessing
public class ApplicationBatchConfig {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public FlatFileItemReader<Customer> itemReader() {
        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("C:/veriseti/customers.csv"));
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob", "age");

        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public CustomerDataProcessor processor() {
        return new CustomerDataProcessor();
    }

    @Bean
    public RepositoryItemWriter<Customer> itemWriter() {
        RepositoryItemWriter<Customer> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(repository);
        itemWriter.setMethodName("save");
        return itemWriter;
    }

    @Bean
    public Step importCustomersStep() {
        return stepBuilderFactory.get("ImportCustomersStep")
                .<Customer, Customer>chunk(10)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                //.taskExecutor(taskExecutor())
                .faultTolerant()
                //.skipLimit(100)
                //.skip(NumberFormatException.class)
                //.noSkip(IllegalArgumentException.class)
                .listener(skipListener())
                .skipPolicy(skipPolicy())
                .build();
    }

    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("importCustomersJob")
                .flow(importCustomersStep())
                .end().build();
    }

//    @Bean
//    public TaskExecutor taskExecutor(){
//        SimpleAsyncTaskExecutor taskExecutor=new SimpleAsyncTaskExecutor();
//        taskExecutor.setConcurrencyLimit(10);
//        return taskExecutor;
//    }

    @Bean
    public SkipPolicy skipPolicy() {
        return new CustomSkipPolicy();
    }

    @Bean
    public SkipListener skipListener() {
        return new BatchStepEventListener();
    }
}