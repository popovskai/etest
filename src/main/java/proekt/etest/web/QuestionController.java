package proekt.etest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import proekt.etest.models.domain.Answer;
import proekt.etest.models.domain.Question;
import proekt.etest.services.QuestionService;
import proekt.etest.services.MapValidationErrorService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{test_id}")
    public ResponseEntity<?> postQuestion(@Valid @RequestBody Question question,
                                          BindingResult result, @PathVariable Long test_id){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        Question question1 =  questionService.saveOrUpdate(question, test_id);
        return new ResponseEntity<Question>(question1, HttpStatus.CREATED);
    }
    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestion(@PathVariable Long questionId){

        Question question = questionService.findQuestionById(questionId);
        if(question != null){
            return new ResponseEntity<Question>(question, HttpStatus.OK);
        }
        return  new ResponseEntity<String>("That question doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/right/{questionId}")
    public ResponseEntity<?> getRightAnswer(@PathVariable Long questionId){

        Answer answer = questionService.findRightAnswer(questionId);
        if(answer != null){
            return new ResponseEntity<Answer>(answer, HttpStatus.OK);
        }
        return  new ResponseEntity<String>("That answer doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/all/{test_id}")
    public Iterable<Question> getAllQuestions(@PathVariable Long test_id){

        return questionService.findAllQuestions(test_id);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long questionId){
        Question question = questionService.findQuestionById(questionId);
        if(question != null ) {
            questionService.deleteQuestion(question);
            return new ResponseEntity<String>("Question is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("That question doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
