package proekt.etest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import proekt.etest.models.domain.Answer;
import proekt.etest.models.domain.Question;
import proekt.etest.services.AnswerService;
import proekt.etest.services.MapValidationErrorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{question_id}")
    public ResponseEntity<?> postAnswer(@Valid @RequestBody Answer answer,
                                        BindingResult result, @PathVariable Long question_id){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        Answer answer1 =  answerService.saveOrUpdate(answer, question_id);
        return new ResponseEntity<Answer>(answer1, HttpStatus.CREATED);
    }
    @GetMapping("/{answerId}")
    public ResponseEntity<?> getAnswer(@PathVariable Long answerId){

        Answer answer = answerService.findAnswerById(answerId);
        if(answer != null){
            return new ResponseEntity<Answer>(answer, HttpStatus.OK);
        }
        return  new ResponseEntity<String>("That answer doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/all/{question_id}")
    public Iterable<Answer> getAllAnswers(@PathVariable Long question_id){

        return answerService.findAllAnswers(question_id);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long answerId){
        Answer answer = answerService.findAnswerById(answerId);
        if(answer != null ) {
            answerService.deleteAnswer(answer);
            return new ResponseEntity<String>("Answer is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("That answer doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
