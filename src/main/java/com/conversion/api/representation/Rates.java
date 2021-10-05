package com.conversion.api.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * A DTO object used to represent the exchange pair which is returned by the third party provider.
 */
public class Rates {

    @JsonProperty("quotes")
    private Map<String, Double> mCurrencies;

    public Map<String, Double> getCurrencies() {
        return mCurrencies;
    }

    public void setCurrencies(Map<String, Double> currencies) {
        this.mCurrencies = currencies;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "currencies=" + mCurrencies +
                '}';
    }
}
