package proekt.etest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proekt.etest.models.domain.Answer;

@Repository
public interface AnswerRepo extends CrudRepository<Answer, Long> {
}
