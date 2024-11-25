package com.example.demoapi.model.entity.people;

import com.example.demoapi.model.dto.episode.EpisodeDto;
import com.example.demoapi.model.entity.episode.Episode;
import com.example.demoapi.model.entity.location.Location;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @Fetch(FetchMode.SUBSELECT)
    private List<EpisodeDto> episode;
    @Column(name = "character_url")
    private String url;
    private String created;


}
