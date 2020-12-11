package com.example.book.service;

import com.example.book.model.mongo.collection.Reservation;
import com.example.book.model.mongo.domain.ReservationDomain;
import com.example.book.model.postgresql.entity.Book;
import com.example.book.repository.mongo.ReservationRepository;
import com.example.book.utils.ErrorCodes;
import com.example.book.utils.ResponseException;
import com.example.book.utils.enums.ReserveStatus;
import com.example.book.utils.mapper.BookMapper;
import com.example.book.utils.mapper.ReservationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;
	private final BookService bookService;
	private final BookMapper bookMapper;

	public ReservationService(ReservationRepository reservationRepository,
							  ReservationMapper reservationMapper, BookService bookService, BookMapper bookMapper) {
		this.reservationRepository = reservationRepository;
		this.reservationMapper = reservationMapper;
		this.bookService = bookService;
		this.bookMapper = bookMapper;
	}

	@Transactional
	public void reserveBook(Long bookId) {
		Book currentBook = bookService.findById(bookId)
				.orElseThrow(() -> new ResponseException(ErrorCodes.ERROR_CODE_BOOK_NOT_FOUNT, "book.not.found"));

		if (currentBook.getReserveStatus().equals(ReserveStatus.RESERVED))
			throw new ResponseException(ErrorCodes.ERROR_CODE_BOOK_ALREADY_RESERVED, "this.book.was.reserved.by.someone.else");

		currentBook.setUpdateDate(Instant.now());
		currentBook.setReserveStatus(ReserveStatus.RESERVED);
		bookService.save(currentBook);

		addReservation(currentBook);
	}

	public void addReservation(Book book) {
		Reservation newReservation = new Reservation();
		newReservation.setCreateDate(Instant.now());
		newReservation.setBook(bookMapper.toDomain(book));

		reservationRepository.save(newReservation);
	}

	public Page<ReservationDomain> getAll(Pageable pageable) {
		return reservationRepository.findAllBy(pageable).map(reservationMapper::toDomain);
	}

	public Page<ReservationDomain> getAllReservedBooks(Pageable pageable) {
		return reservationRepository.findAllByReturnDateIsNull(pageable).map(reservationMapper::toDomain);
	}

	@Transactional
	public void returnReservedBook(String reservedId) {
		Reservation currentReservation = reservationRepository.findById(reservedId)
				.orElseThrow(() -> new ResponseException(ErrorCodes.ERROR_CODES_RESERVATION_NOT_FOUND, "reservation.not.found"));

		if (currentReservation.getReturnDate() != null)
			throw new ResponseException(ErrorCodes.ERROR_CODES_RESERVATION_NOT_FOUND, "this.book.was.returned.before");

		currentReservation.setReturnDate(Instant.now());
		currentReservation.setUpdateDate(Instant.now());

		Book book = bookMapper.toEntityFromDomain(currentReservation.getBook());
		book.setReserveStatus(ReserveStatus.NOT_RESERVED);
		book.setUpdateDate(Instant.now());
		bookService.save(book);

		reservationRepository.save(currentReservation);

	}
}
