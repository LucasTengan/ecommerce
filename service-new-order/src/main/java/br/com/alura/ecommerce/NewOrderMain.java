package br.com.alura.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // número de partições deve ser >= numero de consumidores
        try(var orderDispatcher = new KafkaDispatcher<Order>()) {
            try(KafkaDispatcher<String> emailDispatcher = new KafkaDispatcher<>()) {
                for (var i = 0; i < 10; i++) {

                    var userId = UUID.randomUUID().toString();
                    var orderId = UUID.randomUUID().toString();
                    var value = new BigDecimal(Math.random() * 5000 + 1);

                    var order = new Order(userId, orderId, value);

                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                    var email = "Welcome! We are processing your order!";
                    emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);   // método get informa que devemos esperar terminar de enviar a mensagem, torna síncrono
                }
            }
        }
    }
    /* Produzir mensagens de resume a: criar um produtor (KafkaProducer), criar a mensagem (ProducerRecord)
    enviar a mensagem (send) e colocar algum listener (get)
     */


}
