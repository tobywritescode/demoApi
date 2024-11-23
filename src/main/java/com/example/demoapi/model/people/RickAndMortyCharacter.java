package com.example.demoapi.model.people;

import com.example.demoapi.model.episode.Episode;
import com.example.demoapi.model.location.Location;
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
@Table(name="CHARACTERS")
public class RickAndMortyCharacter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    @OneToOne
    @JoinColumn(name = "origin")
    private Location origin;
    @OneToOne
    @JoinColumn(name = "location")
    private Location location;
    private String image;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "character_episode",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    private List<Episode> episode;
    @Column(name = "character_url")
    private String url;
    private String created;


}
