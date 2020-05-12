package proekt.etest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proekt.etest.models.domain.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User getById(Long id);
}
