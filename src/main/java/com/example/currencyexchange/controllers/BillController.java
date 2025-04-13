package com.example.currencyexchange.controllers;

import com.example.currencyexchange.models.BillRequest;
import com.example.currencyexchange.models.BillResponse;
import com.example.currencyexchange.services.BillCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BillController {

    @Autowired
    private BillCalculationService billCalculationService;

    @PostMapping("/calculate")
    public ResponseEntity<BillResponse> calculateBill(@RequestBody BillRequest billRequest) {
        BillResponse response = billCalculationService.calculateNetPayableAmount(billRequest);
        return ResponseEntity.ok(response);
    }
}