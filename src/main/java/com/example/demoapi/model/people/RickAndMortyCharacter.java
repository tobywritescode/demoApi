package com.example.demoapi.model.people;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="characters")
public class RickAndMortyCharacter implements Serializable {

    @Id
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;

}
