package proekt.etest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proekt.etest.models.domain.Answer;
import proekt.etest.models.domain.Category;
import proekt.etest.repositories.CategoryRepo;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public Category saveOrUpdate(Category category) {

        return categoryRepo.save(category);
    }
    public Category findCategoryById (String categoryId){

        return categoryRepo.findByCategoryIdentifier(categoryId);
    }

    public Category findById(Long categoryId){
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, null));
        return category;
    }

    public Iterable<Category> findAllCategories() {
        return categoryRepo.findAll();
    }
    public void deleteCategory(Category category){
        categoryRepo.delete(category);
    }

    public Category findCategoryByName(String categoryName) {
        return categoryRepo.findByName(categoryName);
    }
}
