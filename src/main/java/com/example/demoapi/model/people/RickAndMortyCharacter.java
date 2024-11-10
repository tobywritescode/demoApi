package com.example.demoapi.model.people;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RickAndMortyCharacter implements Serializable {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;

}
