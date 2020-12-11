package com.example.book.repository.mongo;

import com.example.book.model.mongo.collection.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
	Page<Reservation> findAllBy(Pageable pageable);
	Page<Reservation> findAllByReturnDateIsNull(Pageable pageable);
}
