package com.example.demoapi.batch.reader;

import com.example.demoapi.model.entity.people.UserInfo;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CsvItemReader implements ItemReader<UserInfo> {
    @Override
    public UserInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
