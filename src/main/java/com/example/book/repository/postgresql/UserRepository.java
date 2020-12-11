package com.example.book.repository.postgresql;

import com.example.book.model.postgresql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> , JpaRepository<User,Long> {
	User findByUsername(String username);
	User findByEmail(String email);
}
