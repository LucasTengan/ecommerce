package br.com.alura.ecommerce;

import java.math.BigDecimal;

public class Order {

    private final String userId, orderId;
    private final BigDecimal value;

    public Order(String userId, String orderId, BigDecimal value) {
        this.orderId = orderId;
        this.userId = userId;
        this.value = value;
    }
}