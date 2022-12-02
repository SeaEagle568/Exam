package ua.knu.fknk.examisttp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.fknk.examisttp.models.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
