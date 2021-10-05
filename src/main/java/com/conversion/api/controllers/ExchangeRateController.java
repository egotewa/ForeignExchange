package com.conversion.api.controllers;

import com.conversion.api.constants.Properties;
import com.conversion.api.services.CurrencyService;
import com.conversion.api.representation.Conversion;
import com.conversion.api.validation.annotations.Currency;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/**
 * The controller which handles exchange rate requests.
 */
@RestController
@Validated
@RequestMapping("rates")
@Api(value = "Exchange rates", description = "REST API endpoint that retrieves exchange rates.",
		tags = { "Rates" })
@ApiResponses(value = {
		@ApiResponse(code = 200, message = Properties.ResponseMessage.OK),
		@ApiResponse(code = 404, message = Properties.ResponseMessage.NOT_FOUND),
		@ApiResponse(code = 500, message = Properties.ResponseMessage.SERVER_ERROR)
})
public class ExchangeRateController {

	/** The currency service which will handle connections to the third party provider and exchange calculations. */
	final CurrencyService mCurrencyService;

	@Autowired
	public ExchangeRateController(CurrencyService currencyService) {
		mCurrencyService = currencyService;
	}

	/**
	 * Fetches exchange rates.
	 * @param sourceCurrency	the currency which we would like to convert from.
	 * @param targetCurrency	the currency which we would like to convert to.
	 * @return	A conversion that consists of sourceCurrency, targetCurrency and rate.
	 */
	@GetMapping
	@ApiOperation(value = "Gets currency rates.", tags = { "Rates" })
	@Consumes({ MediaType.APPLICATION_JSON_VALUE })
	@Produces({ MediaType.APPLICATION_JSON_VALUE })
	public Conversion getRate(@Valid @NotNull @Currency @RequestParam("sourceCurrency") String sourceCurrency,
							  @Valid @NotNull @Currency @RequestParam("targetCurrency") String targetCurrency) {
		return new Conversion(sourceCurrency, targetCurrency, mCurrencyService.calculateRate(sourceCurrency, targetCurrency));
	}

}