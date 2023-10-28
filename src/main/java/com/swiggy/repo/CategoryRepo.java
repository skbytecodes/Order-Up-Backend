package com.swiggy.repo;

import com.swiggy.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {


    @Query(value = "select * from category where restaurant_id=?1", nativeQuery = true)
    public List<Category> findAllCategoriesByRestaurantId(Long id);
}
