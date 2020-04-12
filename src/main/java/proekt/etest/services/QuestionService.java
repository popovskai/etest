package proekt.etest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.QuerydslWebConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proekt.etest.models.domain.Answer;
import proekt.etest.models.domain.Question;
import proekt.etest.models.domain.Test;
import proekt.etest.repositories.QuestionRepo;
import proekt.etest.repositories.TestRepo;

import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private TestService testService;

    public Question findQuestionById(Long questionId){
      Question question = questionRepo.findById(questionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, null));
      return question;
    }
    public Question saveOrUpdate(Question question, Long test_id){
        Test test = testService.findTestById(test_id);
        question.setTest(test);
        return questionRepo.save(question);
    }

    public Iterable<Question> findAllQuestions(Long test_id) {
        Test test = testService.findTestById(test_id);
        return test.getQuestions();
    }

    public void deleteQuestion(Question question) {
        questionRepo.delete(question);
    }

    public Answer findRightAnswer(Long questionId) {
        Question question = findQuestionById(questionId);
        return question.getRightAnswer();
    }

}

