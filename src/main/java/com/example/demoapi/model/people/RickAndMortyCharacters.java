package com.example.demoapi.model.people;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RickAndMortyCharacters implements Serializable {
    List<RickAndMortyCharacter> characters;
}
