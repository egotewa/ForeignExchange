package com.conversion.api.representation;

import com.conversion.api.validation.annotations.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The DTO that holds exchange rate and conversion request data.
 * Used only for representation, not persistence.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "sourceCurrency", "targetCurrency", "amount" })
public class Conversion {

  @Currency
  @JsonProperty("sourceCurrency")
  private String mSourceCurrency;

  @Currency
  @JsonProperty("targetCurrency")
  private String mTargetCurrency;

  @NotNull
  @Min(value=0, message = "Amount can not be a negative number.")
  @JsonProperty("amount")
  private Double mAmount;

  public Conversion() {
  }

  public Conversion(String sourceCurrency, String targetCurrency, Double value) {
    this.mSourceCurrency = sourceCurrency;
    this.mTargetCurrency = targetCurrency;
    this.mAmount = value;
  }

  public String getSourceCurrency() {
    return mSourceCurrency;
  }

  public void setSourceCurrency(String mSourceCurrency) {
    this.mSourceCurrency = mSourceCurrency;
  }

  public String getTargetCurrency() {
    return mTargetCurrency;
  }

  public void setTargetCurrency(String mTargetCurrency) {
    this.mTargetCurrency = mTargetCurrency;
  }

  public Double getAmount() {
    return mAmount;
  }

  public void setAmount(Double value) {
    this.mAmount = value;
  }

  @Override
  public String toString() {
    return "Conversion{" +
            "mSourceCurrency='" + mSourceCurrency + '\'' +
            ", mTargetCurrency='" + mTargetCurrency + '\'' +
            ", mAmount=" + mAmount +
            '}';
  }
}