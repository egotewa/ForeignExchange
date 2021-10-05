package com.conversion.api.persistence.entities;

import com.conversion.api.representation.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.MessageFormat;
import java.time.LocalDate;

/**
 * The DTO class which holds transaction data.
 */
@Entity
@Table(name="Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(Views.Coversion.class)
    private Long id;

    @Column(name = "date")
    @JsonView(Views.ConversionListing.class)
    private LocalDate date;

    @Column(name = "conversionType", length = 10)
    @JsonView(Views.ConversionListing.class)
    private String conversionType;

    @Column(name = "targetAmount")
    @JsonView(Views.Coversion.class)
    private Double targetAmount;

    @Column(name = "rate")
    @JsonView(Views.ConversionListing.class)
    private Double rate;

    public Transaction() {
    }

    public Transaction(String sourceCurrency, String targetCurrency,
                       Double targetAmount, Double rate) {
        date = LocalDate.now();
        conversionType = MessageFormat.format("{0} to {1}", sourceCurrency, targetCurrency);
        this.targetAmount = targetAmount;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getConversionType() {
        return conversionType;
    }

    public void setConversionType(String conversionType) {
        this.conversionType = conversionType;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", conversionType='" + conversionType + '\'' +
                ", targetAmount=" + targetAmount +
                ", rate=" + rate +
                '}';
    }
}
