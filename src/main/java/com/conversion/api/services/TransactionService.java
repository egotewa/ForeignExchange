package com.conversion.api.services;

import com.conversion.api.persistence.entities.Transaction;
import com.conversion.api.persistence.repositories.TransactionRepository;
import com.conversion.api.utils.FilterUtils;
import com.conversion.api.validation.exceptions.NoRecordsFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * The service which filters and persists transactions. Acts as a mediator.
 */
@Service
public class TransactionService {

    /** The repository that will operate with Transactions in the database. */
    private final TransactionRepository mTransactionRepository;

    public TransactionService(TransactionRepository pagingRepository) {
        this.mTransactionRepository = pagingRepository;
    }

    /**
     * Fetches transactions from the database depending on the filter. Supports paging.
     * @param pageNo    the page number.
     * @param pageSize  the page size.
     * @param filter    the Transaction filter.
     * @param value     the filter value.
     * @param <T>       type representing all possible (and future) filter value types.
     * @return  A list of filtered Transactions with the specified paging.
     */
    public <T> List<Transaction> getFilteredTransactions(Integer pageNo, Integer pageSize, String filter, String value) {
        T filterValue = FilterUtils.determineFilterValueType(filter, value);
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Transaction> pagedResult = filterValue instanceof Long ?
                                            mTransactionRepository.findAllById((Long) filterValue, paging) :
                                                mTransactionRepository.findAllByDate((LocalDate) filterValue, paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        throw new NoRecordsFoundException("No records according to the given filter found.");
    }

    /**
     * Persists Transaction objects.
     * @param t the transaction to persist.
     * @return  the persisted transaction. Does not return null.
     */
    public Transaction saveTransaction(Transaction t) {
        return mTransactionRepository.save(t);
    }
}

