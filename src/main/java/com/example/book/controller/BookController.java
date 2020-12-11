package com.example.book.controller;

import com.example.book.model.postgresql.domain.BookDomain;
import com.example.book.service.BookService;
import com.example.book.utils.filter.criteria.BookCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/not-reserved")
	public ResponseEntity<Page<BookDomain>> getNotReservedBooks(Pageable pageable) {
		return ResponseEntity.ok(bookService.getNotReservedBooks(pageable));
	}

	@GetMapping("/search")
	public ResponseEntity<Page<BookDomain>> getBookBySearch(Pageable pageable, BookCriteria bookCriteria) {
		return ResponseEntity.ok(bookService.getBookByFilter(bookCriteria, pageable));
	}
}
