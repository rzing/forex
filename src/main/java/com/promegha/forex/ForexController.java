package com.promegha.forex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/forex")
public class ForexController {
    @Autowired
    private ForexService forexService;

    @GetMapping("/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue
            (@PathVariable String from, @PathVariable String to) throws ForexConversionException {

        return forexService.getCurrencyExchangeValue(from, to);

    }
}
