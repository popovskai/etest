package proekt.etest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proekt.etest.models.domain.UserTest;

@Repository
public interface UserTestRepo extends CrudRepository <UserTest, Long> {
}
