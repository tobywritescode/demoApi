package com.example.demoapi.model.repo;

import com.example.demoapi.model.people.RickAndMortyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RickAndMortyCharactersRepository extends JpaRepository<RickAndMortyCharacter, Long> {

}
