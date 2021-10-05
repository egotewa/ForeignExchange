package com.conversion.api.utils;

import com.conversion.api.constants.Properties;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Util class that facilitates operations with URL.
 */
public class UrlUtils {

    /**
     * Constructs the URL that is used to connect to the third party currency exchange provider.
     * @param sourceCurrency    the currency which we would like to convert from.
     * @param targetCurrency    the currency which we would like to convert to.
     * @return  A String representation of the URL.
     */
    public static String constructLiveRateURL(String sourceCurrency, String targetCurrency) {
        return UriComponentsBuilder.fromUriString(Properties.BASE_LIVE_URL)
                .queryParam(Properties.URLQueryParameters.ACCESS_KEY, Properties.USER_ACCESS_KEY)
                .queryParam(Properties.URLQueryParameters.CURRENCES, sourceCurrency + "," + targetCurrency)
                .queryParam(Properties.URLQueryParameters.FORMAT, 1).toUriString();
    }
}
