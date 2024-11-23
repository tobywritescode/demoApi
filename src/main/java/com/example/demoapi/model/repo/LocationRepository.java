package com.example.demoapi.model.repo;

import com.example.demoapi.model.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location getLocationByUrl(String url);
}
