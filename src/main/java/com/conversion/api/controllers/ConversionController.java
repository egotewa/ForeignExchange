package com.conversion.api.controllers;

import com.conversion.api.constants.Properties;
import com.conversion.api.persistence.entities.Transaction;
import com.conversion.api.representation.Conversion;
import com.conversion.api.representation.Views;
import com.conversion.api.services.CurrencyService;
import com.conversion.api.services.TransactionService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/**
 * The controller which handles conversion REST API requests.
 */
@RestController
@Validated
@RequestMapping("convert")
@Api(value = "Convert currencies", description = "REST API endpoint that converts one currency to another.",
        tags = { "Conversions" })
@ApiResponses(value = {
        @ApiResponse(code = 200, message = Properties.ResponseMessage.OK),
        @ApiResponse(code = 404, message = Properties.ResponseMessage.NOT_FOUND),
        @ApiResponse(code = 500, message = Properties.ResponseMessage.SERVER_ERROR)
})
public class ConversionController {

    /** The transaction service which will handle transaction operations. */
    final TransactionService mTransactionService;

    /** The currency service which will handle connections to the third party provider and exchange calculations. */
    final CurrencyService mCurrencyService;

    @Autowired
    public ConversionController(TransactionService transactionService,
                                CurrencyService currencyService) {
        mTransactionService = transactionService;
        mCurrencyService = currencyService;
    }

    /**
     * Converts the given amount of the source currency to a given amount of the target currency.
     * @param conversion    The request body.
     * @return  A view of Transacion that contains only the target amount and the transaction id.
     */
    @PostMapping
    @ApiOperation(value = "Converts currencies.", tags = {"Conversions"})
    @JsonView(Views.Coversion.class)
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public Transaction convertCurrency(@RequestBody @Valid Conversion conversion) {
        Double rate = mCurrencyService.calculateRate(conversion.getSourceCurrency(), conversion.getTargetCurrency());
        return mTransactionService.saveTransaction(new Transaction(conversion.getSourceCurrency(),
                conversion.getTargetCurrency(), conversion.getAmount() * rate, rate));
    }
}
