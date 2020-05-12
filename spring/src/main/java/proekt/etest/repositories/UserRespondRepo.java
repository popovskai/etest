package proekt.etest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proekt.etest.models.domain.UserRespond;

@Repository
public interface UserRespondRepo extends CrudRepository<UserRespond, Long> {
}
