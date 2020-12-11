package com.example.book.utils.filter;

import com.example.book.model.postgresql.entity.Book;
import com.example.book.model.postgresql.entity.Book_;
import com.example.book.utils.filter.criteria.BookCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookFiltering {
	@PersistenceContext
	private final EntityManager entityManager;

	public BookFiltering(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Book> filter(BookCriteria bookCriteria, Pageable pageable) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
		List<Predicate> predicates = new ArrayList<>();


		if (bookCriteria.getTitle() != null) {
			if (bookCriteria.getTitle().getContains() != null)
				predicates.add(cb.like(root.get(Book_.TITLE), "%" + bookCriteria.getTitle().getContains() + "%"));
			else if (bookCriteria.getTitle().getStartWith() != null)
				predicates.add(cb.like(root.get(Book_.TITLE), bookCriteria.getTitle().getContains() + "%"));
			else if (bookCriteria.getReserveStatus().getEquals() != null)
				predicates.add(cb.equal(root.get(Book_.TITLE), bookCriteria.getTitle().getEquals()));
		}
		if (bookCriteria.getReserveStatus() != null) {
			if (bookCriteria.getReserveStatus().getEquals() != null)
				predicates.add(cb.equal(root.get(Book_.RESERVE_STATUS), bookCriteria.getReserveStatus().getEquals()));
			else if (bookCriteria.getReserveStatus().getIn() != null)
				predicates.add(getValueIn(cb, root, Book_.RESERVE_STATUS, bookCriteria.getReserveStatus().getIn()));
		}

		TypedQuery<Book> typedQuery = entityManager.createQuery(cq.select(root).where(predicates.toArray(new Predicate[predicates.size()])));
		if (pageable.isPaged())
			typedQuery.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize());
		return typedQuery.getResultList();
	}

	private CriteriaBuilder.In<Integer> getValueIn(CriteriaBuilder cb, Root<Book> root, String column, List<Integer> values) {
		CriteriaBuilder.In<Integer> in = cb.in(root.get(column));
		for (Integer value : values) {
			in.value(value);
		}
		return in;
	}
}
