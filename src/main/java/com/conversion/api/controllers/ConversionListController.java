package com.conversion.api.controllers;

import com.conversion.api.constants.Properties;
import com.conversion.api.persistence.entities.Transaction;
import com.conversion.api.representation.Views;
import com.conversion.api.services.TransactionService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * The controller which handles transaction listing requests.
 */
@RestController
@Validated
@RequestMapping("list")
@Api(value = "Convert currencies",
        description = "REST API endpoint that lists conducted transactions.",
        tags = { "Listing" })
@ApiResponses(value = {
        @ApiResponse(code = 200, message = Properties.ResponseMessage.OK),
        @ApiResponse(code = 404, message = Properties.ResponseMessage.NOT_FOUND),
        @ApiResponse(code = 500, message = Properties.ResponseMessage.SERVER_ERROR)
})
public class ConversionListController {

    /** The transaction service which will handle transaction operations. */
    final TransactionService transactionService;

    @Autowired
    public ConversionListController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Lists transactions based on the filter. Provides paging.
     * @param pageIndex the page number. Default is 1.
     * @param pageSize  the page size. Default is 10.
     * @param filter    the filter which will be used to reduce entries.
     * @param value     the filter value.
     * @return  A JSON view of Transaction that includes all fields.
     */
    @GetMapping()
    @ApiOperation(value = "Gets a list of transactions according to the specified filter.",
            tags = { "Listing" })
    @JsonView(Views.ConversionListing.class)
    @Consumes({ MediaType.APPLICATION_JSON_VALUE })
    @Produces({ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Transaction>> getTransactionsListing(@RequestParam(defaultValue = "1")
                                                                    @Min(value = 1, message = "Page index must be at least 1.") Integer pageIndex,
                                                                    @RequestParam(defaultValue = "10")
                                                                    @Min(value = 1, message = "Page size must be at least 1.") Integer pageSize,
                                                                    @RequestParam @NotBlank
                                                                    @NotNull(message = "At least one of transactionId, transactionDate must be present.")
                                                                            String filter,
                                                                    @RequestParam @NotBlank
                                                                    @NotNull(message = "Please provide a value for the filter parameter.")
                                                                            String value) {
        return new ResponseEntity<>(transactionService.getFilteredTransactions(pageIndex - 1, pageSize, filter, value),
                                    new HttpHeaders(),
                                    HttpStatus.OK);
    }
}
