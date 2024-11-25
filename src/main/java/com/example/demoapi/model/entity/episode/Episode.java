package com.example.demoapi.model.entity.episode;

import com.example.demoapi.model.entity.people.RickAndMortyCharacter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="EPISODES")
public class Episode  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String air_date;
    private String episode;
    @ManyToMany(mappedBy = "episode", fetch = FetchType.EAGER)
    private List<RickAndMortyCharacter> characters;
    @Column(name = "episode_url")
    private String url;
    private String created;
}
