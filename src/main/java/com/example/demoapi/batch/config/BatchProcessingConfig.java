package com.example.demoapi.batch.config;

import com.example.demoapi.batch.reader.CsvItemReader;
import com.example.demoapi.batch.writer.UserInfoWriter;
import com.example.demoapi.model.entity.people.UserInfo;
import com.example.demoapi.batch.listener.JobCompletionNotificationListener;
import com.example.demoapi.batch.processor.UserInfoProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchProcessingConfig {

    @Bean
    public FlatFileItemReader<UserInfo> reader() {
        return new FlatFileItemReaderBuilder<UserInfo>()
                .name("userInfoReader")
                .resource(new ClassPathResource("input/input.csv"))
                .delimited()
                .names("firstName", "lastName")
                .targetType(UserInfo.class)
                .build();
    }

    @Bean
    public UserInfoProcessor processor(){
        return new UserInfoProcessor();
    }

    @Bean
    public Job readAndInsertUserInfoCsv(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener){
        return new JobBuilder("readAndInsertUserInfoCsv", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<UserInfo> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<UserInfo>()
                .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<UserInfo, UserInfo> chunk(3, transactionManager)
                .reader(new CsvItemReader())
                .processor(new UserInfoProcessor())
                .writer(new UserInfoWriter())
                .build();
    }



}
