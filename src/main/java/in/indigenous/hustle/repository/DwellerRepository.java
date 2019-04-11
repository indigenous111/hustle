package in.indigenous.hustle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.indigenous.hustle.entities.Dweller;

@Repository
public interface DwellerRepository extends JpaRepository<Dweller, Long>{

	Dweller findByName(String name);
}
