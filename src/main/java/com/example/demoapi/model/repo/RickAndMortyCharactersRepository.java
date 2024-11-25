package com.example.demoapi.model.repo;

import com.example.demoapi.model.entity.people.RickAndMortyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RickAndMortyCharactersRepository extends JpaRepository<RickAndMortyCharacter, Long> {

    List<RickAndMortyCharacter> findRickAndMortyCharacterById(Long id);
}
