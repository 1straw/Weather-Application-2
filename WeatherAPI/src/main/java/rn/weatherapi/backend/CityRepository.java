package rn.weatherapi.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import rn.weatherapi.backend.entity.City;

@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
public interface CityRepository extends JpaRepository<City, Long> {
    // Du kan lägga till anpassade frågemetoder här om du behöver
}
