package proekt.etest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proekt.etest.models.domain.Category;
@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
    Category findByCategoryIdentifier(String categoryId);
}
