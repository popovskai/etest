package proekt.etest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proekt.etest.models.domain.Answer;
import proekt.etest.models.domain.Question;
import proekt.etest.models.domain.UserRespond;
import proekt.etest.models.domain.UserTest;
import proekt.etest.repositories.UserRespondRepo;

import java.util.List;

@Service
public class UserRespondService {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserRespondRepo userRespondRepo;
    @Autowired
    private UserTestService userTestService;

    public UserRespond addRespond(Long user_testId, Long questionId, Long respondId) {

        UserRespond userRespond = new UserRespond();
        Question question = questionService.findQuestionById(questionId);
        Answer answer = questionService.findRightAnswer(questionId);
        Answer respond = answerService.findAnswerById(respondId);
        UserTest userTest = userTestService.findUserTestById(user_testId);

        if(answer.getId().equals(respondId)){
            Integer score = userTest.getUser_score();
            score++;
            userTest.setUser_score(score);

        }
        userTest.getUserRespond().add(userRespond);
        List<Answer> answers = userRespond.getUserAnswers();
        answers.add(respond);
        userRespond.setUserAnswers(answers);
        return userRespondRepo.save(userRespond);

    }
}
