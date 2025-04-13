package com.example.currencyexchange.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class CurrencyExchangeService {

    @Value("${exchange.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable("exchangeRates")
    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().path("conversion_rates").path(targetCurrency).asDouble();
        } else {
            throw new RuntimeException("Failed to retrieve exchange rate.");
        }
    }
}