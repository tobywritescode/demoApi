package com.example.demoapi.model.people;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    public String name;
    public String email;
    public Integer age;
}
