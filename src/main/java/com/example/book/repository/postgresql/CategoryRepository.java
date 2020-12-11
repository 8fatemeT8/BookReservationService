package com.example.book.repository.postgresql;

import com.example.book.model.postgresql.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CrudRepository<Category, Long> {
}
