package com.example.currencyexchange.services;

import com.example.currencyexchange.models.BillRequest;
import com.example.currencyexchange.utils.DiscountCalculator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTest {

    @Test
    public void testEmployeeDiscount() {
        BillRequest request = new BillRequest();
        request.setUserType("EMPLOYEE");
        request.setCustomerTenure(3);
        request.setTotalAmount(200);

        BillRequest.Item item1 = new BillRequest.Item();
        item1.setCategory("OTHER");
        item1.setPrice(100);
        BillRequest.Item item2 = new BillRequest.Item();
        item2.setCategory("GROCERIES");
        item2.setPrice(100);

        request.setItems(Arrays.asList(item1, item2));

        double discount = DiscountCalculator.calculateDiscount(request);
        // 30% of 100 = 30, and $5 for every $100 = 10
        assertEquals(40, discount);
    }

    @Test
    public void testAffiliateDiscount() {
        BillRequest request = new BillRequest();
        request.setUserType("AFFILIATE");
        request.setCustomerTenure(1);
        request.setTotalAmount(250);

        BillRequest.Item item1 = new BillRequest.Item();
        item1.setCategory("OTHER");
        item1.setPrice(200);
        BillRequest.Item item2 = new BillRequest.Item();
        item2.setCategory("GROCERIES");
        item2.setPrice(50);

        request.setItems(Arrays.asList(item1, item2));

        double discount = DiscountCalculator.calculateDiscount(request);
        // 10% of 200 = 20, and $5 for every $100 = 10
        assertEquals(30, discount);
    }

    @Test
    public void testLoyalCustomerDiscount() {
        BillRequest request = new BillRequest();
        request.setUserType("CUSTOMER");
        request.setCustomerTenure(3);
        request.setTotalAmount(100);

        BillRequest.Item item = new BillRequest.Item();
        item.setCategory("OTHER");
        item.setPrice(100);

        request.setItems(Arrays.asList(item));

        double discount = DiscountCalculator.calculateDiscount(request);
        // 5% of 100 = 5, and $5 for $100 = 5 => Total = 10
        assertEquals(10, discount);
    }

    @Test
    public void testNoPercentageDiscount() {
        BillRequest request = new BillRequest();
        request.setUserType("CUSTOMER");
        request.setCustomerTenure(1);
        request.setTotalAmount(200);

        BillRequest.Item item1 = new BillRequest.Item();
        item1.setCategory("GROCERIES");
        item1.setPrice(200);

        request.setItems(Arrays.asList(item1));

        double discount = DiscountCalculator.calculateDiscount(request);
        // No percentage discount on groceries, only $5 per $100
        assertEquals(10, discount);
    }
}