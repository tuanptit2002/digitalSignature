package com.banking.digital_signature_demo.dto;

public class TransferRequest {

    private String fromAccount;
    private String toAccount;
    private double amount;
    // getters and setters
    @Override
    public String toString() {
        return "TransferRequest{" +
                "fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                '}';
    }
}
