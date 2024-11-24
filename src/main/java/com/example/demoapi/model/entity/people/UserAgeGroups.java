package com.example.demoapi.model.entity.people;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAgeGroups {
   List<UserInfo> adults;
   List<UserInfo> children;
   List<UserInfo> youngAdults;
}
