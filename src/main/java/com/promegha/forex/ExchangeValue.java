package com.promegha.forex;

import java.util.Date;

public class ExchangeValue {
    private String fromCurrency;
    private String toCurrency;
    private Double exchangeRate;
    private Date timeStamp;

    public ExchangeValue(String fromCurrency, String toCurrency, Double exchangeRate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
        this.timeStamp = new Date();
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
