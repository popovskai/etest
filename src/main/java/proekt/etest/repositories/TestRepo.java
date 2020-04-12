package proekt.etest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proekt.etest.models.domain.Test;

@Repository
public interface TestRepo extends CrudRepository<Test, Long> {
    Test findByName(String test_id);
}
