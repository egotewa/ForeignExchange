package com.conversion.api;

import com.conversion.api.constants.ParameterType;
import com.conversion.api.persistence.entities.Transaction;
import com.conversion.api.persistence.repositories.TransactionRepository;
import com.conversion.api.services.TransactionService;
import com.conversion.api.validation.exceptions.NoRecordsFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

/**
 * Unit tests for the TransactionService class.
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTests {

    /** The repository that will operate with Transactions in the database. */
    @Autowired
    TransactionRepository mTransactionRepository;

    /** The transaction service which will handle transaction operations. */
    @Autowired
    TransactionService mTransactionService;

    /**
     * Creates some dummy data and persists it in the database for testing purposes.
     */
    @BeforeAll
    private void createDummyTransactions() {
        Transaction dummyTransaction1 = new Transaction("BGN", "EUR", 10.25, 0.51);
        dummyTransaction1.setDate(LocalDate.EPOCH);
        dummyTransaction1.setId(1L);
        Transaction dummyTransaction2 = new Transaction("USD", "EUR", 5.15, 0.863765);
        Transaction dummyTransaction3 = new Transaction("USD", "BGN", 80.72, 1.68919);
        mTransactionRepository.save(dummyTransaction1);
        mTransactionRepository.save(dummyTransaction2);
        mTransactionRepository.save(dummyTransaction3);
    }

    /**
     * Checks whether filtering of transactions work as expected using the current date.
     */
    @Test
    void testFilterTransactionsByCurrentDate() {
        List<Transaction> transactionList = mTransactionService.getFilteredTransactions(0, 2,
                                    ParameterType.transactionDate.name(), LocalDate.now().toString());
        Assertions.assertEquals(transactionList.size(), 2);
    }

    /**
     * Checks whether filtering of transactions work as expected using previous date.
     */
    @Test
    void testFilterTransactionsByEpochDate() {
        List<Transaction> transactionList = mTransactionService.getFilteredTransactions(0, 1,
                ParameterType.transactionDate.name(), LocalDate.EPOCH.toString());
        Assertions.assertEquals(transactionList.size(), 1);
    }

    /**
     * Checks whether filtering of transactions work as expected using a transaction id.
     */
    @Test
    void testFilterTransactionsById() {
        List<Transaction> transactionList = mTransactionService.getFilteredTransactions(0, 3,
                                                        ParameterType.transactionId.name(), "1");
        Assertions.assertEquals(transactionList.size(), 1);
    }

    /**
     * Checks whether an exception is thrown when an invalid transaction id is passed.
     */
    @Test
    void testFilteredTransactionsThrowingException() {
        Assertions.assertThrows(NoRecordsFoundException.class,
                            () -> mTransactionService.getFilteredTransactions(0, 3,
                                    ParameterType.transactionId.name(), "999"));
    }
}
