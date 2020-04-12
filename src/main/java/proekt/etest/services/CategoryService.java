package proekt.etest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Iterable<Category> findAllCategories() {
        return categoryRepo.findAll();
    }
    public void deleteCategory(Category category){
        categoryRepo.delete(category);
    }
}
