package proekt.etest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import proekt.etest.models.domain.*;
import proekt.etest.services.CategoryService;
import proekt.etest.services.MapValidationErrorService;
import proekt.etest.services.TestService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    TestService testService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    @PostMapping("/{category_id}")
    public ResponseEntity<?> addTestToCategory(@Valid @RequestBody Test test,
                                            BindingResult result, @PathVariable Long category_id){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        Test test1 = testService.addTest(category_id, test);

        return new ResponseEntity<Test>(test1, HttpStatus.CREATED);

    }
    @GetMapping("/{testId}")
    public ResponseEntity<?> getTest(@PathVariable Long testId){

        Test test = testService.findTestById(testId);
        if(test != null){
            return new ResponseEntity<Test>(test, HttpStatus.OK);
        }
        return  new ResponseEntity<String>("That test doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/all/{category_id}")
    public Iterable<Test> getAllTestByCategory(@PathVariable Long category_id){
        return testService.getAllByCategory(category_id);
    }

    @DeleteMapping("/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Long testId){
        Test test = testService.findTestById(testId);
        if(test != null ) {
            testService.deleteTest(test);
            return new ResponseEntity<String>("Test is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("That test doesn't exist", HttpStatus.NOT_FOUND);
    }


}
