package com.promegha.forex;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ForexRepository {
    private HashMap<String, Double> exchangeRateMap = new HashMap<>(10);

    public ForexRepository() {
        exchangeRateMap.put("USD", 1.0);
        exchangeRateMap.put("EUR", 0.892721);
        exchangeRateMap.put("GBP", 0.766073);
        exchangeRateMap.put("INR", 69.7551);
        exchangeRateMap.put("AUD", 1.42509);
        exchangeRateMap.put("AED", 3.67250);
        //exchangeRateMap.put("CAD", 1.34382);
        //exchangeRateMap.put("SGD", 1.36118);
    }

    public ExchangeValue getCurrencyExchangeValue(String from, String to) throws ForexConversionException {
        if(from == null || from.isEmpty()) {
            throw new ForexConversionException("FROM currency cannot be NULL or EMPTY");
        }

        if(to == null || to.isEmpty()) {
            throw new ForexConversionException("TO currency cannot be NULL or EMPTY");
        }
        from = from.toUpperCase();
        to = to.toUpperCase();

        Double conversionFactorForFromCurrency = exchangeRateMap.get(from);
        Double conversionFactorForToCurrency = exchangeRateMap.get(to);

        if(conversionFactorForFromCurrency == null) {
            throw new ForexConversionException("Invalid FROM currency");
        }

        if(conversionFactorForToCurrency == null) {
            throw new ForexConversionException("Invalid TO currency");
        }

        return new ExchangeValue(from, to, conversionFactorForToCurrency / conversionFactorForFromCurrency);
    }
}

