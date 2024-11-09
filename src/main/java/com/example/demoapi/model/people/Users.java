package com.example.demoapi.model.people;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements Serializable {
    public List<User> userList;
}
