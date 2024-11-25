package com.example.demoapi.model.dto.episode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
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
@Table(name="EPISODES")
public class EpisodeDto implements Serializable {
    @Id
    private long id;
    private String name;
    private String air_date;
    private String episode;
    @Column(name = "episode_url")
    private String url;
    private String created;
}

