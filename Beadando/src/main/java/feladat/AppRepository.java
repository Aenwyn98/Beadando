package feladat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import feladat.App;

@Repository
public interface AppRepository extends CrudRepository<App, Long>{

	
	
	
}
