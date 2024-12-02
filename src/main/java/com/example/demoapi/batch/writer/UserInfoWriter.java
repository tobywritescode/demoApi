package com.example.demoapi.batch.writer;

import com.example.demoapi.model.entity.people.UserInfo;
import io.micrometer.common.lang.NonNull;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class UserInfoWriter implements ItemWriter<UserInfo> {
    @Override
    public void write(@NonNull Chunk<? extends UserInfo> userInfos) {
           try {
               jdbcBatchItemWriter().write(userInfos);
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
    }

    public JdbcBatchItemWriter<UserInfo> jdbcBatchItemWriter(){
        return new JdbcBatchItemWriterBuilder<UserInfo>()
                .sql("INSERT INTO userInfo (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource())
                .beanMapped()
                .build();
    }

    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
