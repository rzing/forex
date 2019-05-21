package com.promegha.forex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForexService {
    @Autowired
    private ForexRepository forexRepository;

    public ExchangeValue getCurrencyExchangeValue(String from, String to) throws ForexConversionException {
        return forexRepository.getCurrencyExchangeValue(from, to);
    }
}
