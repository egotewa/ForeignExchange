package com.conversion.api.services;

import com.conversion.api.constants.Properties;
import com.conversion.api.representation.ConversionInformation;
import com.conversion.api.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Holds the business logic associated with exchange rates.
 * Fetches data from the third party provider and calculates currency rates.
 */
@Service
public class CurrencyService {

    /** The rest template object that will be used when communicating with the third party currency provider. */
    final RestTemplate mLiveRatesTemplate;

    @Autowired
    public CurrencyService(RestTemplate restTemplate) {
        mLiveRatesTemplate = restTemplate;
    }

    /**
     * Fetches currency rates from the third party provider.
     * @param sourceCurrency    the currency which we would like to convert from.
     * @param targetCurrency    the currency which we would like to convert to.
     * @return  The object containing exchange rate information.
     */
    public ConversionInformation retrieveLiveRates(String sourceCurrency, String targetCurrency) {
        return mLiveRatesTemplate.getForObject(
                    UrlUtils.constructLiveRateURL(sourceCurrency, targetCurrency), ConversionInformation.class);
    }

    /**
     * Calculates the rate based on the source and target currencies.
     * @param sourceCurrency    the currency which we would like to convert from.
     * @param targetCurrency    the currency which we would like to convert to.
     * @return  The conversion rate.
     */
    public Double calculateRate(String sourceCurrency, String targetCurrency) {
        ConversionInformation conversionInformation = retrieveLiveRates(sourceCurrency, targetCurrency);
        return conversionInformation.getRates().getCurrencies().get(Properties.CURRENCY_USD.concat(targetCurrency)) /
                conversionInformation.getRates().getCurrencies().get(Properties.CURRENCY_USD.concat(sourceCurrency));
    }
}
