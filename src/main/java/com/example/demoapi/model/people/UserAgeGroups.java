package com.example.demoapi.model.people;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAgeGroups {
   List<User> adults;
   List<User> children;
   List<User> youngAdults;
}
