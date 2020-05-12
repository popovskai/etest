package proekt.etest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proekt.etest.models.domain.Category;
import proekt.etest.models.domain.Test;
import proekt.etest.repositories.CategoryRepo;
import proekt.etest.repositories.TestRepo;

@Service
public class TestService {
    @Autowired
    private TestRepo testRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private CategoryService categoryService;


    public Test addTest(Long category_id, Test test) {
        Category category = categoryService.findById(category_id);
        test.setCategory(category);
        return testRepo.save(test);

    }

    public Test findTestById(Long testId) {
        Test test = testRepo.findById(testId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, null));
        return test;
    }

    public Iterable<Test> getAllByCategory(Long category_id) {
        Category category = categoryService.findById(category_id);
        return category.getTests();
    }

    public void deleteTest(Test test) {
        //testRepo.delete(test);
        testRepo.deleteById(test.getId());
    }
}
