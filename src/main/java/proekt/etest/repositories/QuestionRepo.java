package proekt.etest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proekt.etest.models.domain.Question;
@Repository
public interface QuestionRepo extends CrudRepository<Question, Long> {
}
