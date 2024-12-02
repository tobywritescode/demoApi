package com.example.demoapi.batch.processor;

import com.example.demoapi.model.entity.people.UserInfo;
import org.springframework.batch.item.ItemProcessor;

public class UserInfoProcessor implements ItemProcessor<UserInfo, UserInfo> {
    @Override
    public UserInfo process(UserInfo item) throws Exception {
        return null;
    }
}
