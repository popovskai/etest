package proekt.etest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proekt.etest.models.domain.Answer;
import proekt.etest.models.domain.Question;
import proekt.etest.repositories.AnswerRepo;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private QuestionService questionService;

    public Answer findAnswerById(Long answerId){
        Answer answer = answerRepo.findById(answerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, null));
        return answer;
    }
    public Answer saveOrUpdate(Answer answer, Long question_id) {
        Question question = questionService.findQuestionById(question_id);
        if(answer.isRight()){
            question.setRightAnswer(answer);
            answer.setRightQuestion(question);
        }
        answer.setQuestion(question);

        return answerRepo.save(answer);
    }

    public Iterable<Answer> findAllAnswers(Long question_id) {
        Question question = questionService.findQuestionById(question_id);
        return question.getAnswers();
    }

    public void deleteAnswer(Answer answer) {
        answerRepo.delete(answer);
    }

}
