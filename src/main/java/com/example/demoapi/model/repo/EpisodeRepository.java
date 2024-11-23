package com.example.demoapi.model.repo;

import com.example.demoapi.model.episode.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    Episode getEpisodeByUrl(String url);
}
