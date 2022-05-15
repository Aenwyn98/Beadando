package feladat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import feladat.Background;

@Repository
public interface BackgroundRepository extends CrudRepository<Background, Long>{
	
}
