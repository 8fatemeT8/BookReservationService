package com.example.book.repository.postgresql;

import com.example.book.model.postgresql.entity.Book;
import com.example.book.utils.enums.ReserveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>, CrudRepository<Book,Long> {
	Page<Book> findAllByReserveStatus(ReserveStatus reserveStatus, Pageable pageable);
}
