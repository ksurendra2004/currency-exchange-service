package com.exchange.controller;

import com.exchange.model.ExchangeModel;
import com.exchange.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    @PostMapping("/add-exchange-details")
    public String addExchange(@RequestBody ExchangeModel exchangeModel) {
        exchangeModel.setPort(getAppPort());
        currencyExchangeRepository.save(exchangeModel);
        return "Successfully added exchange details";
    }

    @GetMapping("/currency-exchange/fromCurrency/{fromCurrency}/toCurrency/{toCurrency}")
    public ExchangeModel retrieveExchangeValue(@PathVariable String fromCurrency,
                          @PathVariable String toCurrency) {
        BigDecimal conversionMultiple = null;

        if (fromCurrency != null && toCurrency != null) {
            if (fromCurrency.equalsIgnoreCase("USD")
                    && toCurrency.equalsIgnoreCase("INR")) {
                conversionMultiple = BigDecimal.valueOf(82);
            }
            if (fromCurrency.equalsIgnoreCase("INR")
                    && toCurrency.equalsIgnoreCase("USD")) {
                conversionMultiple
                        = BigDecimal.valueOf(0.013);
            }
            if (fromCurrency.equalsIgnoreCase("EUR")
                    && toCurrency.equalsIgnoreCase("INR")) {
                conversionMultiple = BigDecimal.valueOf(87);
            }
            if (fromCurrency.equalsIgnoreCase("AUD")
                    && toCurrency.equalsIgnoreCase("INR")) {
                conversionMultiple = BigDecimal.valueOf(55);
            }
        }
        // setting the port
        ExchangeModel exchangeModel = new ExchangeModel(1000L, fromCurrency, toCurrency, conversionMultiple);
        exchangeModel.setPort(getAppPort());
        return exchangeModel;
    }

    private int getAppPort() {
        return Integer.parseInt(
                Objects.requireNonNull(environment.getProperty("local.server.port")));
    }
}
