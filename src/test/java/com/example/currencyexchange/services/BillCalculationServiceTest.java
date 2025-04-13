package com.example.currencyexchange.services;

import com.example.currencyexchange.models.BillRequest;
import com.example.currencyexchange.models.BillResponse;
import com.example.currencyexchange.services.BillCalculationService;
import com.example.currencyexchange.services.CurrencyExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BillCalculationServiceTest {

    @Mock
    private CurrencyExchangeService currencyExchangeService;

    @InjectMocks
    private BillCalculationService billCalculationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateNetPayableAmount_forEmployee() {
        BillRequest request = new BillRequest();
        request.setUserType("EMPLOYEE");
        request.setCustomerTenure(3);
        request.setOriginalCurrency("USD");
        request.setTargetCurrency("EUR");
        request.setTotalAmount(300);

        BillRequest.Item item1 = new BillRequest.Item();
        item1.setCategory("OTHER");
        item1.setPrice(150);

        BillRequest.Item item2 = new BillRequest.Item();
        item2.setCategory("GROCERIES");
        item2.setPrice(50);

        BillRequest.Item item3 = new BillRequest.Item();
        item3.setCategory("OTHER");
        item3.setPrice(100);

        request.setItems(Arrays.asList(item1, item2, item3));

        when(currencyExchangeService.getExchangeRate("USD", "EUR")).thenReturn(0.9);

        BillResponse response = billCalculationService.calculateNetPayableAmount(request);

        double expectedTotalAfterDiscount = (300 - ((250 * 0.3) + 15)) * 0.9; // 30% on 250 + $5 per $100
        assertEquals(expectedTotalAfterDiscount, response.getNetPayableAmount(), 0.01);
        assertEquals("EUR", response.getCurrency());
    }
}
