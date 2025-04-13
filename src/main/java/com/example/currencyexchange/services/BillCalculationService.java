package com.example.currencyexchange.services;

import com.example.currencyexchange.models.BillRequest;
import com.example.currencyexchange.models.BillResponse;
import com.example.currencyexchange.utils.DiscountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillCalculationService {

    @Autowired
    private CurrencyExchangeService exchangeService;

    public BillResponse calculateNetPayableAmount(BillRequest request) {
        double discount = DiscountCalculator.calculateDiscount(request);
        double totalAfterDiscount = request.getTotalAmount() - discount;

        double exchangeRate = exchangeService.getExchangeRate(
                request.getOriginalCurrency(),
                request.getTargetCurrency()
        );

        double totalInTargetCurrency = totalAfterDiscount * exchangeRate;

        return new BillResponse(totalInTargetCurrency, request.getTargetCurrency());
    }
}
