
package com.example.currencyexchange.models;

public class BillResponse {
    private double netPayableAmount;
    private String currency;

    public BillResponse(double netPayableAmount, String currency) {
        this.netPayableAmount = netPayableAmount;
        this.currency = currency;
    }

    public double getNetPayableAmount() { return netPayableAmount; }
    public void setNetPayableAmount(double netPayableAmount) { this.netPayableAmount = netPayableAmount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}