package com.example.book.service;

import com.example.book.model.postgresql.domain.BookDomain;
import com.example.book.model.postgresql.entity.Book;
import com.example.book.repository.postgresql.BookRepository;
import com.example.book.utils.enums.ReserveStatus;
import com.example.book.utils.filter.BookFiltering;
import com.example.book.utils.filter.criteria.BookCriteria;
import com.example.book.utils.mapper.BookMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
	private final BookRepository bookRepository;
	private final BookMapper bookMapper;
	private final BookFiltering bookFiltering;

	public BookService(BookRepository bookRepository, BookMapper bookMapper, BookFiltering bookFiltering) {
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
		this.bookFiltering = bookFiltering;
	}

	@Transactional
	public Page<BookDomain> getNotReservedBooks(Pageable pageable) {
		return bookRepository.findAllByReserveStatus(ReserveStatus.NOT_RESERVED, pageable).map(bookMapper::toDomain);
	}

	public void save(Book book) {
		bookRepository.save(book);
	}

	public Optional<Book> findById(Long bookId) {
		return bookRepository.findById(bookId);
	}

	public Page<BookDomain> getBookByFilter(BookCriteria bookCriteria, Pageable pageable) {
		return new PageImpl<BookDomain>(bookFiltering.filter(bookCriteria,pageable)
				.stream().map(bookMapper::toDomain).collect(Collectors.toList()),pageable,countAll());
	}

	public Long countAll(){
		return bookRepository.count();
	}
}
