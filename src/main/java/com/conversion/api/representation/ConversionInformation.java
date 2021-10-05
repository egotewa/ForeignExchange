package com.conversion.api.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * A DTO that holds the exchange rate information which is received by the third party provider.
 * Note that it serializes and deserializes only essential data.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversionInformation {

  @JsonUnwrapped
  @JsonProperty("quotes")
  private Rates mRates;

  public ConversionInformation() {
  }

  public Rates getRates() {
    return mRates;
  }

  public void setRates(Rates rates) {
    this.mRates = rates;
  }

  @Override
  public String toString() {
    return "ConversionInformation{" +
            "rates=" + mRates +
            '}';
  }
}