package com.example.book.controller;

import com.example.book.model.mongo.domain.ReservationDomain;
import com.example.book.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public ResponseEntity<Page<ReservationDomain>> getAll(Pageable pageable) {
		return ResponseEntity.ok(reservationService.getAll(pageable));
	}

	@GetMapping("/reserved-book")
	public ResponseEntity<Page<ReservationDomain>> getAllReservedBooks(Pageable pageable){
		return ResponseEntity.ok(reservationService.getAllReservedBooks(pageable));
	}

	@PutMapping("/reserve-book/{id}")
	public ResponseEntity<?> reserveBook(@PathVariable("id") Long bookId) {
		reservationService.reserveBook(bookId);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/return-book/{id}")
	public ResponseEntity<?> returnReservedBook(@PathVariable("id") String reservedId){
		reservationService.returnReservedBook(reservedId);
		return ResponseEntity.ok().build();
	}
}
