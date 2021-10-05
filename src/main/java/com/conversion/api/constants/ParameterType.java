package com.conversion.api.constants;

/**
 * The enum used to represent filter types when filtering transactions.
 */
public enum ParameterType {

    transactionId("transactionId"),
    transactionDate("transactionDate");

    String mValue;

    ParameterType(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String mValue) {
        this.mValue = mValue;
    }
}
