package com.conversion.api.persistence.repositories;

import com.conversion.api.persistence.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * The repository used to handle database operations with the Transaction DTO.
 */
@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {
    Page<Transaction> findAllByDate(LocalDate date, Pageable pageable);
    Page<Transaction> findAllById(Long id, Pageable pageable);
}
