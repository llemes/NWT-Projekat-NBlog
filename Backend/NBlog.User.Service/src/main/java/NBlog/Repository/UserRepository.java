package NBlog.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NBlog.DataModel.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	User findUser(@Param("name") String name);
	User delete(@Param("name") String name);
}
