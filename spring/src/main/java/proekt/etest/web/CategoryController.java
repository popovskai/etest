package proekt.etest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import proekt.etest.models.domain.Category;
import proekt.etest.services.CategoryService;
import proekt.etest.services.MapValidationErrorService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> postCategory(@Valid @RequestBody Category category, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        Category cat =  categoryService.saveOrUpdate(category);
        return new ResponseEntity<Category>(cat, HttpStatus.CREATED);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable String categoryId){

        Category category = categoryService.findCategoryById(categoryId);
        if(category != null){
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        }
        return  new ResponseEntity<String>("That category doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/all")
    public Iterable<Category> getAllCategories(){
        return categoryService.findAllCategories();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        Category category = categoryService.findById(categoryId);
        if(category != null ) {
            categoryService.deleteCategory(category);
            return new ResponseEntity<String>("Category is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("That category doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
