package com.conversion.api;

import com.conversion.api.representation.ConversionInformation;
import com.conversion.api.representation.Rates;
import com.conversion.api.services.CurrencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the CurrencyService class.
 */
@SpringBootTest
public class CurrencyServiceTests {

    /** The currency service which will handle connections to the third party provider and exchange calculations. */
    @Autowired
    CurrencyService mCurrencyService;

    /**
     * Tests the rate calculation.
     */
    @Test
    void testCalculateRate() {
        mCurrencyService = new CurrencyService(mockData());
        Double conversionRate = mCurrencyService.calculateRate("BGN", "EUR");
        Assertions.assertEquals(conversionRate, 0.5113486345526554);
    }

    /**
     * Mocks the rest template to avoid actual calls to the third party provider.
     * Creates dummy data.
     * @return  the rest template that will be used for testing purposes.
     */
    private RestTemplate mockData() {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        Rates rates = new Rates();
        rates.setCurrencies(new HashMap<>() {{
            put("USDBGN", 1.68919);
            put("USDEUR", 0.863765);
        }});
        ConversionInformation conversionInformation = new ConversionInformation();
        conversionInformation.setRates(rates);
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(conversionInformation);
        return restTemplate;
    }

}
