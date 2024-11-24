package com.example.demoapi.model.entity.people;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements Serializable {
    public List<UserInfo> userInfoList;
}
