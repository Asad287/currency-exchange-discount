package com.example.currencyexchange.utils;

import com.example.currencyexchange.models.BillRequest;

public class DiscountCalculator {

    public static double calculateDiscount(BillRequest request) {
        double discountPercentage = 0;

        if ("EMPLOYEE".equalsIgnoreCase(request.getUserType())) {
            discountPercentage = 0.30;
        } else if ("AFFILIATE".equalsIgnoreCase(request.getUserType())) {
            discountPercentage = 0.10;
        } else if ("CUSTOMER".equalsIgnoreCase(request.getUserType()) && request.getCustomerTenure() > 2) {
            discountPercentage = 0.05;
        }

        double nonGroceryTotal = request.getItems().stream()
                .filter(i -> !"GROCERIES".equalsIgnoreCase(i.getCategory()))
                .mapToDouble(BillRequest.Item::getPrice)
                .sum();

        double percentageDiscount = nonGroceryTotal * discountPercentage;
        double fixedDiscount = Math.floor(request.getTotalAmount() / 100) * 5;

        return percentageDiscount + fixedDiscount;
    }
}

