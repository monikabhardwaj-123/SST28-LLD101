package com.example.payments;

import java.util.Objects;

public class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "client must not be null");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        SafeCashPayment pmt = client.createPayment(amountCents, customerId);
        return pmt.confirm();
    }
}
