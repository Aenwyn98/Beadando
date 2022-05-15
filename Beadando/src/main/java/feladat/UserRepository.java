package feladat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import feladat.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	
	
	
}
